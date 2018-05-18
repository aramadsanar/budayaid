from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from budayaid_database_config import Base, Province

engine = create_engine('sqlite:///budayaid.db')

DBSession = sessionmaker(bind=engine)
session = DBSession()

#Masukkan provinsi disini :)
def add_jawa():
	banten = Province(name = "banten", friendly_name = "Banten", island="jawa")
	session.add(banten)
	session.commit()
	
	dkijakarta = Province(name = "dkijakarta", friendly_name = "DKI Jakarta", island="jawa")
	session.add(dkijakarta)
	session.commit()
	
	jawabarat = Province(name = "jawabarat", friendly_name = "Jawa Barat", island="jawa")
	session.add(jawabarat)
	session.commit()

	jawatengah = Province(name = "jawatengah", friendly_name = "Jawa Tengah", island="jawa")
	session.add(jawatengah)
	session.commit()
	
	diyogyakarta = Province(name = "diyogyakarta", friendly_name = "DI Yogyakarta", island="jawa")
	session.add(diyogyakarta)
	session.commit()

	jawatimur = Province(name = "jawatimur", friendly_name = "Jawa Timur", island="jawa")
	session.add(jawatimur)
	session.commit()

def add_sumatera():
	sumaterautara = Province(name = "sumaterautara", friendly_name = "Sumatera Utara", island="sumatera")
	session.add(sumaterautara)
	session.commit()

	sumateraselatan = Province(name = "sumateraselatan", friendly_name = "Sumatera Selatan", island="sumatera")
	session.add(sumateraselatan)
	session.commit()

	aceh = Province(name = "aceh", friendly_name = "Aceh", island="sumatera")
	session.add(aceh)
	session.commit()
	
	bengkulu = Province(name = "bengkulu", friendly_name = "Bengkulu", island="sumatera")
	session.add(bengkulu)
	session.commit()
	
	jambi = Province(name = "jambi", friendly_name = "Jambi", island="sumatera")
	session.add(jambi)
	session.commit()

	kepulauan_bangka_belitung = Province(name = "kepulauan_bangka_belitung", friendly_name = "Kepulauan Bangka Belitung", island="sumatera")
	session.add(kepulauan_bangka_belitung)
	session.commit()
	
	kepulauan_riau = Province(name = "kepulauan_riau", friendly_name = "Kepulauan Riau", island="sumatera")
	session.add(kepulauan_riau)
	session.commit()
	
	lampung = Province(name = "lampung", friendly_name = "Lampung", island="sumatera")
	session.add(lampung)
	session.commit()
	
	riau = Province(name = "riau", friendly_name = "Riau", island="sumatera")
	session.add(riau)
	session.commit()

	sumaterabarat = Province(name = "sumaterabarat", friendly_name = "Sumatera Barat", island="sumatera")
	session.add(sumaterabarat)
	session.commit()

def add_kalimantan():
	kalimantanbarat = Province(name = "kalimantanbarat", friendly_name = "Kalimantan Barat", island="kalimantan")
	session.add(kalimantanbarat)
	session.commit()
	
	kalimantantimur = Province(name = "kalimantantimur", friendly_name = "Kalimantan Timur", island="kalimantan")
	session.add(kalimantantimur)
	session.commit()

	kalimantanselatan = Province(name = "kalimantanselatan", friendly_name = "Kalimantan Selatan", island="kalimantan")
	session.add(kalimantanselatan)
	session.commit()
	
	kalimantantengah = Province(name = "kalimantantengah", friendly_name = "Kalimantan Tengah", island="kalimantan")
	session.add(kalimantantengah)
	session.commit()

	kalimantanutara = Province(name = "kalimantanutara", friendly_name = "Kalimantan Utara", island="kalimantan")
	session.add(kalimantanutara)
	session.commit()
	
def add_sulawesi():
	gorontalo = Province(name = "gorontalo", friendly_name = "Gorontalo", island="sulawesi")
	session.add(gorontalo)
	session.commit()

	sulawesibarat = Province(name = "sulawesibarat", friendly_name = "Sulawesi Barat", island="sulawesi")
	session.add(sulawesibarat)
	session.commit()
	
	sulawesiselatan = Province(name = "sulawesiselatan", friendly_name = "Sulawesi Selatan", island="sulawesi")
	session.add(sulawesiselatan)
	session.commit()

	sulawesitengah = Province(name = "sulawesitengah", friendly_name = "Sulawesi Tengah", island="sulawesi")
	session.add(sulawesitengah)
	session.commit()

	sulawesitenggara = Province(name = "sulawesitenggara", friendly_name = "Sulawesi Tenggara", island="sulawesi")
	session.add(sulawesitenggara)
	session.commit()

def add_bali_and_nusa_tenggara():
	bali = Province(name="bali", friendly_name="Bali", island="bali_nt")
	session.add(bali)
	session.commit()

	nusa_tenggara_barat = Province(name="nusa_tenggara_barat", friendly_name="Nusa Tenggara Barat", island="bali_nt")
	session.add(nusa_tenggara_barat)
	session.commit()

	nusa_tenggara_timur = Province(name="nusa_tenggara_timur", friendly_name="Nusa Tenggara Timur", island="bali_nt")
	session.add(nusa_tenggara_timur)
	session.commit()

def add_papua_and_maluku():
	papuabarat = Province(name="papuabarat", friendly_name = "Papua Barat", island="papua_maluku")
	session.add(papuabarat)
	session.commit()

	papua = Province(name="papua", friendly_name = "Papua", island="papua_maluku")
	session.add(papua)
	session.commit()

	maluku = Province(name="maluku", friendly_name = "Maluku", island="papua_maluku")
	session.add(maluku)
	session.commit()

	malukuutara = Province(name="malukuutara", friendly_name = "Maluku Utara", island="papua_maluku")
	session.add(malukuutara)
	session.commit()

def main():
	add_jawa()
	add_sumatera()
	add_kalimantan()
	add_sulawesi()
	add_bali_and_nusa_tenggara()
	add_papua_and_maluku()

if __name__ == '__main__':
	main()