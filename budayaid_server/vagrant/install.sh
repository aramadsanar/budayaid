#!/bin/bash
# DO NOT RENAME THIS SCRIPT FILE!
sudo apt-get update
sudo apt-get install -y python3-pip unzip
sudo -H pip3 install sqlalchemy flask
mkdir budayaid_srv
sudo chown armadanasar budayaid_srv
unzip budayaid_backend_install_package.zip -d ./budayaid_srv
cd budayaid_srv
mkdir imagepool
sudo chown armadanasar *
python3 budayaid_srv_app.py
cd ..
rm install.sh
