import sys
from sqlalchemy import Column, ForeignKey, Integer, String

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship, sessionmaker
from sqlalchemy import create_engine

Base = declarative_base()

class Passphrase(Base):
	__tablename__ = 'passphrase'
	passhash = Column(String(250), primary_key = True)



if __name__ == '__main__':
	engine = create_engine("sqlite:///passphrase.db")

	Base.metadata.create_all(engine)

	DBSession = sessionmaker(bind=engine)
	session = DBSession()
	pass_one = Passphrase(passhash = "1950bc7336a1f553555aa1570c29b25b")
	pass_two = Passphrase(passhash = "6d402585a637be868c7187a9c4a003bb")
	pass_three = Passphrase(passhash = "91043af95641e7c3422104cb69e3067c")

	session.add(pass_one)
	session.commit()

	session.add(pass_two)
	session.commit()

	session.add(pass_three)
	session.commit()
