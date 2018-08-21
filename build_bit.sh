# Update & Upgrade the System
sudo apt-get update
sudo apt-get upgrade

# Install dependencies there might be more based on your system
# However below instructions are for the fresh Ubuntu install/server
# Please carefully watch the logs because if something could not be install
# You have to make sure it is installed properly by trying the command or that particular
# dependency again

sudo apt-get install build-essential libtool autotools-dev autoconf pkg-config libssl-dev
sudo apt-get install libboost-all-dev
sudo apt-get install libqt5gui5 libqt5core5a libqt5dbus5 qttools5-dev qttools5-dev-tools libprotobuf-dev protobuf-compiler
sudo apt-get install libqrencode-dev autoconf openssl libssl-dev libevent-dev
sudo apt-get install libminiupnpc-dev

# Download Bitcoin Source code
# ----------------------------
cd ~
git clone https://github.com/bitcoin/bitcoin.git

# Bitcoin uses the Berkley DB 4.8
# We need to install it as well
# Download & Install Berkley DB
# -----------------------------
cd ~
mkdir bitcoin/db4/
wget 'http://download.oracle.com/berkeley-db/db-4.8.30.NC.tar.gz'
tar -xzvf db-4.8.30.NC.tar.gz
cd db-4.8.30.NC/build_unix/
../dist/configure --enable-cxx --disable-shared --with-pic --prefix=/home/theusername/bitcoin/db4/
make install

# Compile Bitcoin with Berkley DB 4.8
# -----------------------------------
cd ~/bitcoin/
./autogen.sh
# below command ./configure may return with error for dependencies
# you need to make sure that it returns with no error
# If it does please install the dependencies and rerun the /autogen.sh command again and then below command again
./configure LDFLAGS="-L/home/theusername/bitcoin/db4/lib/" CPPFLAGS="-I/home/theusername/bitcoin/db4/include/"

# below command may take 5-10 minutes based on your system
make -s -j5

# If all went well you will be able to access the binary at below location
cd ~/bitcoin/
./src/bitcoind
./src/bitcoin-qt
./src/bitcoin-cli
