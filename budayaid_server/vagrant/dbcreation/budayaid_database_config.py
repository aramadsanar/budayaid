import sys
from sqlalchemy import Column, ForeignKey, Integer, String

from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship, sessionmaker
from sqlalchemy import create_engine

Base = declarative_base()

class Province(Base):
	__tablename__ = 'province'
	id = Column(Integer, primary_key = True)
	name = Column(String(250), nullable = False)
	friendly_name = Column(String(250), nullable = False)
	island = Column(String(50))

	@property
	def serialize(self):
		return {
			'id' : self.id,
			'name' : self.name,
			'friendly_name' : self.friendly_name,
			'island': self.island
		}

class Categories(Base):
	__tablename__ = 'categories'
	id = Column(Integer, primary_key = True)
	name = Column(String(250), nullable = False)
	friendly_name = Column(String(250), nullable = False)

class Budaya(Base):
	__tablename__ = 'budaya'
	id = Column(Integer, primary_key = True)
	name = Column(String(80), nullable=False)
	description = Column(String(250))
	image_url = Column(String(250))
	google_search_term = Column(String(250))
	

	province_id = Column(Integer, ForeignKey('province.id'))
	province = relationship(Province)

	category = Column(Integer, ForeignKey('categories.id'))
	categories = relationship(Categories)

	@property
	def serialize(self):
		return {
			'id' : self.id,
			'name' : self.name,
			'description' : self.description,
			'image_url' : self.image_url,
			'google_search_term' : self.google_search_term,
			'province_id' : self.province_id,
			'category' : self.category,
		}
	
engine = create_engine("sqlite:///budayaid.db")

Base.metadata.create_all(engine)
