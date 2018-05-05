from flask import Flask, request, render_template, url_for, flash, redirect, send_from_directory
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Province, Budaya
import os
from werkzeug import secure_filename
from pathlib import Path

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = './imagepool/'
ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg'])
engine = create_engine('sqlite:///budayaid.db?check_same_thread=False')
DBSession = sessionmaker(bind=engine)
session = DBSession()

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

@app.route('/')
def homepage():
	return redirect(url_for('add_budaya'))

@app.route('/add', methods=['GET', 'POST'])
def add_budaya():
	if request.method == 'POST':
		if 'image_file' not in request.files:
			flash("You must upload a file!")
			return redirect(url_for('add_budaya'))
		
		image_file = request.files['image_file']

		if image_file.filename == '':
			flash("Filename could not be empty!")
			print("Filename could not be empty!")
			return redirect(url_for('add_budaya'))
		if not allowed_file(image_file.filename):
			flash("File type is not alowed!")
			print("Filename could not be empty!")
			return redirect(url_for('add_budaya'))

		image_url = ""
		alt_filename = check_file_existence_and_create_alternate_filename_if_exists(image_file.filename)
		print("alternate filename - dup handler: ")
		print(alt_filename)
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
	edited_item = session.query(Budaya).filter_by(id=item_id).first()
	print(request.form['name'])
	print(request.form['description'])
	print(request.form['google_search_term'])
	edited_item.name = request.form['name']
	edited_item.description = request.form['description']
	edited_item.google_search_term = request.form['google_search_term']
	session.add(edited_item)
	session.commit()
	flash("budaya id=" + str(item_id) + " has been updated :)")
	return redirect(url_for('moderationtool'))

@app.route('/moderationtool/detail/<int:item_id>/delete', methods=['GET', 'POST'])
def moderationtool_detail_delete(item_id):
	deleted_item = session.query(Budaya).filter_by(id=item_id).first()
	if request.method == 'POST':
		session.delete(deleted_item)
		session.commit()
		flash("budaya id=" + str(item_id) + " has been deleted!")
		return redirect(url_for('moderationtool'))
	else:
		return render_template('budayaid_moderation_tool_delete.html', deleted_item = deleted_item)

app.secret_key = "budayaid_char6014"
app.debug = True

def main():
	app.run(host = '0.0.0.0', port=6014)

if __name__ == '__main__':
	main()