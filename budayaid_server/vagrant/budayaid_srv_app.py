from flask import Flask, request, render_template, url_for, flash, redirect, send_from_directory, jsonify
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Province, Budaya, Categories
import os
from werkzeug import secure_filename
from pathlib import Path
import hashlib
from budayaid_create_passphrase_store import Passphrase
import time, datetime

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = './imagepool/'
ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg'])
engine = create_engine('sqlite:///budayaid.db?check_same_thread=False')
DBSession = sessionmaker(bind=engine)
session = DBSession()

passphrase_engine = create_engine('sqlite:///passphrase.db?check_same_thread=False')
passphrase_DBSession = sessionmaker(bind=passphrase_engine)
passphrase_session = passphrase_DBSession()


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def get_filename_without_extension(filename):
	return filename.rsplit('.', 1)[0]

def get_extension_from_filename(filename):
	return filename.rsplit('.', 1)[1]

def check_file_existence_and_create_alternate_filename_if_exists(filename):
	file_check = Path(os.path.join(app.config['UPLOAD_FOLDER'], secure_filename(filename)))
	if file_check.exists():
		dup_counter = 0
		dup_file_exists = False
		while dup_file_exists == False:
			new_filename = get_filename_without_extension(secure_filename(filename)) + str('_' + str(dup_counter)) + '.' + get_extension_from_filename(filename)
			new_path = Path(os.path.join(app.config['UPLOAD_FOLDER'], new_filename))
			dup_file_check = Path(new_path)
			if dup_file_check.exists():
				dup_counter += 1
			else:
				return new_filename
	else:
		return filename

def create_alternate_filename(filename):
	ts = time.time()
	st = datetime.datetime.fromtimestamp(ts).strftime('%Y_%m_%d_%H_%M_%S')

	new_filename = get_filename_without_extension(filename) + st + "." + get_extension_from_filename(filename)
	return new_filename

def authenticate(passphrase):
	if passphrase == None:
		return False
	hashed_passphrase = hashlib.md5(passphrase.encode())
	verify = passphrase_session.query(Passphrase).filter_by(passhash = hashed_passphrase.hexdigest()).first()
	#print(verify.passhash)
	if verify == None:
		return False
	else:
		return True

@app.route('/')
def homepage():
	return redirect(url_for('add_budaya'))

@app.route('/add', methods=['GET', 'POST'])
def add_budaya():
	if request.method == 'POST':
		if not authenticate(request.form['input_passphrase']):
			flash("wrong password!")
			return redirect(url_for('add_budaya'))
		else:
			flash("okay")
			#return redirect(url_for('add_budaya'))
		if 'image_file' not in request.files:
			flash("You must upload a file!")
			return redirect(url_for('add_budaya'))
		
		image_file = request.files['image_file']

		if ' ' in image_file.filename:
			flash("No spaces allowed in uploaded file name!")
			return redirect(url_for('add_budaya'))
		if image_file.filename == '':
			flash("Filename could not be empty!")
			#print("Filename could not be empty!")
			return redirect(url_for('add_budaya'))
		if not allowed_file(image_file.filename):
			flash("File type is not alowed!")
			#print("Filename could not be empty!")
			return redirect(url_for('add_budaya'))

		image_url = ""
		#tes = create_alternate_filename(image_file.filename)
		#print(tes)
		alt_filename = create_alternate_filename(image_file.filename)
		#print("alternate filename - dup handler: ")
		#print(alt_filename)
		if alt_filename == image_file.filename:
			image_file.save(os.path.join(app.config['UPLOAD_FOLDER'], secure_filename(image_file.filename)))
			image_url = image_file.filename
		else:
			image_file.save(os.path.join(app.config['UPLOAD_FOLDER'], secure_filename(alt_filename)))
			image_url = alt_filename

		name=request.form['name']
		description=request.form['description']
		prov_val = request.form['province']
		#print("hahahihi")
		#print(prov_val)
		province = session.query(Province).filter_by(id=int(prov_val)).first()
		google_search_term = request.form['google_search_term']
		category_val = request.form['category']
		category = session.query(Categories).filter_by(id=int(category_val)).first()
		#deprecated
		#WARNING: Dummy province in use! REPLACE BEFORE RELEASE!
		#dumprov = session.query(Province).filter_by(name='dummytes').first()
				
		newBudaya = Budaya(name=name, description=description, image_url=image_url, google_search_term=google_search_term, province=province, category=category_val)
		
		session.add(newBudaya)
		session.commit()
		
		flash("New budaya added!")
		
		return redirect(url_for('add_budaya'))
	else:
		provinces = session.query(Province).all()
		categories = session.query(Categories).all()
		return render_template('budayaid_add_budaya_page.html', provinces=provinces, categories=categories)

@app.route('/getImage/<string:image_file_name>/')
def get_image(image_file_name):
	path = os.path.join(app.config['UPLOAD_FOLDER'], secure_filename(image_file_name))
	print(path)
	return send_from_directory('imagepool', secure_filename(image_file_name))

@app.route('/moderationtool/')
def moderationtool():
	budayas = session.query(Budaya).all()
	return render_template('budayaid_moderation_tool.html', budayas=budayas)

@app.route('/moderationtool/detail/<int:item_id>')
def moderationtool_detail(item_id):
	item = session.query(Budaya).filter_by(id=item_id).first()
	return render_template('budayaid_moderation_tool_detail.html', item=item)

@app.route('/moderationtool/detail/<int:item_id>/edit', methods=['POST'])
def moderationtool_detail_edit(item_id):
	if not authenticate(request.form['input_passphrase']):
		flash("wrong password")
		return redirect(url_for('moderationtool'))

	edited_item = session.query(Budaya).filter_by(id=item_id).first()
	print(request.form['name'])
	print(request.form['description'])
	print(request.form['google_search_term'])
	
	if request.form['name'] == '':
		edited_item.name = edited_item.name
	else:
		edited_item.name = request.form['name']
	
	if request.form['description'] == '':
		edited_item.description = edited_item.description
	else:
		edited_item.description = request.form['description']
	
	if request.form['google_search_term'] == '':
		edited_item.google_search_term = edited_item.google_search_term
	else:
		edited_item.google_search_term = request.form['google_search_term']
	
	session.add(edited_item)
	session.commit()
	flash("budaya id=" + str(item_id) + " has been updated :)")
	return redirect(url_for('moderationtool'))

@app.route('/moderationtool/detail/<int:item_id>/delete', methods=['GET', 'POST'])
def moderationtool_detail_delete(item_id):

	deleted_item = session.query(Budaya).filter_by(id=item_id).first()
	if request.method == 'POST':
		if not authenticate(request.form['input_passphrase']):
			flash("wrong password")
			return redirect(url_for('moderationtool'))
		session.delete(deleted_item)
		session.commit()
		flash("budaya id=" + str(item_id) + " has been deleted!")
		return redirect(url_for('moderationtool'))
	else:
		return render_template('budayaid_moderation_tool_delete.html', deleted_item = deleted_item)

#The one and only JSON REST API Endpoint :)
@app.route('/getJSONBudayasByProvinceId/<int:province_id>')
def get_budayas_by_province_id(province_id):
	province_filter = session.query(Province).filter_by(id=province_id).first()
	#category_filter = session.query(Categories).filter_by(id = category_id).first()
	budayas = session.query(Budaya).filter_by(province_id = province_filter.id).all()
	return jsonify(budaya=[b.serialize for b in budayas])

@app.route('/getJSONBudayasByProvinceIdAndCategory/<int:province_id>/<int:category_id>/')
def get_budayas_by_province_id_and_category(province_id, category_id):
	province_filter = session.query(Province).filter_by(id=province_id).first()
	category_filter = session.query(Categories).filter_by(id = category_id).first()
	budayas = session.query(Budaya).filter_by(province_id = province_filter.id, category = category_filter.id).all()
	return jsonify(budaya=[b.serialize for b in budayas])

@app.route('/getJSONProvinces/')
def get_provinces():
	provinces = session.query(Province).all()
	return jsonify(provinces=[p.serialize for p in provinces])

@app.route('/getJSONProvincesByIslandName/<string:islandName>')
def get_provinces_by_island_name(islandName):
	provinces = session.query(Province).filter_by(island=islandName).all()
	return jsonify(provinces=[p.serialize for p in provinces])

@app.route('/getJSONProvincesNameOnly/')
def get_provinces_name_only():
	provinces = session.query(Province).all()
	province_name_list = []
	for p in provinces:
		province_name_list.append(p.name)
	return jsonify(provinces=[pname for pname in province_name_list])


app.secret_key = "budayaid_char6014"
app.debug = True

def main():
	app.run(host = '0.0.0.0', port=6014)

if __name__ == '__main__':
	main()