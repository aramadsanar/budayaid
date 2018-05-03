from flask import Flask, request, render_template, url_for, redirect
app = Flask(__name__)
from database_config import Restaurant, MenuItem, Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
import cgi

engine = create_engine("sqlite:///restaurantmenu.db?check_same_thread=False")
DBSession = sessionmaker(bind=engine)

session = DBSession()


@app.route('/restaurants/<int:resid>/')
def helloworld(resid):
	oneres = session.query(Restaurant).filter_by(id=resid).first()
	if oneres != None:
		items = session.query(MenuItem).filter_by(restaurant_id=oneres.id).all()
		'''output = ""
		for i in items:
			output += i.name
			output += " "
			output += i.price
			output += "<br/>"
			output += i.description
			output += '<br/> <br/>'
		return output'''
		return render_template('menu.html', restaurant=oneres, items=items)

	else:
		return "restaurant not found!"
# Task 1: Create route for newMenuItem function here

@app.route('/restaurants/<int:restaurant_id>/add/', methods=['GET', 'POST'])
def newMenuItem(restaurant_id):
	if request.method == 'POST':
		newMenu = MenuItem(name=request.form['name'], restaurant_id=restaurant_id)
		session.add(newMenu)
		session.commit()
		return redirect(url_for('helloworld', resid=restaurant_id))
	else:
		return render_template('addmenu.html', restaurant_id=restaurant_id)
	return "page to create a new menu item. Task 1 complete!"

# Task 2: Create route for editMenuItem function here

@app.route('/restaurants/<int:restaurant_id>/<int:menu_id>/edit/', methods=['GET', 'POST'])
def editMenuItem(restaurant_id, menu_id):
	if request.method == 'POST':
		mi = session.query(MenuItem).filter_by(id=menu_id).first()
		mi.name = request.form['name']
		session.add(mi)
		session.commit()
		return redirect(url_for('helloworld', resid=restaurant_id))
	else:
		editedItem = session.query(MenuItem).filter_by(id=menu_id).first()
		return render_template('editmenu.html', restaurant_id=restaurant_id, menu_id=menu_id, i=editedItem)
	return "tes"

# Task 3: Create a route for deleteMenuItem function here

@app.route('/restaurants/<int:restaurant_id>/<int:menu_id>/delete/', methods=['GET', 'POST'])
def deleteMenuItem(restaurant_id, menu_id):
	editedItem = session.query(MenuItem).filter_by(id=menu_id).first()
	if request.method == 'POST':
		a = session.query(MenuItem).filter_by(id=menu_id).first()
		session.delete(a)
		session.commit()
		return redirect(url_for('helloworld', resid=restaurant_id))
	else:
		return render_template('deletemenus.html', item=editedItem)



if __name__ == '__main__':
	rest = session.query(Restaurant).all()

	for r in rest:
		print(r.id)
	app.debug = True
	app.run(host = '0.0.0.0', port=8080)