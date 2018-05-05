from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Province, Budaya

engine = create_engine('sqlite:///budayaid.db?check_same_thread=False')
DBSession = sessionmaker(bind=engine)
session = DBSession()

dummyprov = Province(name="dummyprovince", friendly_name = "Dummy Province")
session.add(dummyprov)
session.commit()	