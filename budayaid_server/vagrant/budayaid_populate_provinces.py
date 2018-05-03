from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Province

engine = create_engine('sqlite:///budayaid.db')

DBSession = sessionmaker(bind=engine)
session = DBSession()

#Masukkan provinsi disini :)
def add_jawa():

def add_sumatera():

def add_kalimantan():

def add_sulawesi():

def add_bali_and_nusa_tenggara():

def add_papua_and_maluku():

def main():
	add_jawa()
	add_sumatera()
	add_kalimantan()
	add_sulawesi()
	add_bali_and_nusa_tenggara()
	add_papua_and_maluku()

if __name__ == '__main__':
	main()