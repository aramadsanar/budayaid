from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Province, Budaya
engine = create_engine('sqlite:///budayaid.db?check_same_thread=False')
DBSession = sessionmaker(bind=engine)
session = DBSession()
budayas = session.query(Budaya).all()
for b in budayas:
	print(b.name)
	print(b.description)
	print(b.image_url)
	print(b.google_search_term)
	print()