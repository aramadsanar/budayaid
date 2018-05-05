#!/bin/bash
# DO NOT RENAME THIS SCRIPT FILE!
sudo apt-get update
sudo apt-get install -y python3-pip unzip
sudo -H pip3 install sqlalchemy flask
mkdir budayaid_srv
sudo chown armadanasar budayaid_srv
unzip budayaid_install_package.zip -d ./budayaid_srv
cd budayaid_srv
mkdir imagepool
sudo chown armadanasar *
python3 budayaid_database_config.py
python3 budayaid_add_dummy_province.py
python3 budayaid_srv_app.py
cd ..
rm install.sh
