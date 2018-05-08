from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Categories

engine = create_engine('sqlite:///budayaid.db?check_same_thread=False')
DBSession = sessionmaker(bind=engine)
session = DBSession()

dummyprov = Categories(name="makanan", friendly_name = "Makanan")
session.add(dummyprov)
session.commit()

dummyprov1 = Categories(name="pakaian", friendly_name = "Pakaian")
session.add(dummyprov1)
session.commit()

dummyprov2 = Categories(name="kesenian", friendly_name = "Kesenian")
session.add(dummyprov2)
session.commit()

dummyprov3 = Categories(name="bangunan", friendly_name = "Bangunan")
session.add(dummyprov3)
session.commit()

dummyprov4 = Categories(name="lainnya", friendly_name = "Lainnya")
session.add(dummyprov4)
session.commit()	