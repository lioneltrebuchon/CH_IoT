#!/usr/bin/env python3

class dbClassObj:
    # Abstractor for the database keys from the Centos MariaSQL
    lst_home = ['home','homes','appart','appartment','apt','house','room','Zimmer','Wohnung']
    lst_zip = ['zip','PIN']
    nbrs_zip = [5]
    lst_street = ['address','adress','adresse','street','Strasse','rue']
    lst_address = ['where','location','address','adresse']
    lst_city = ['in', 'close to']
    lst_secure = ['secure', 'safe']
    lst_comfortable = ['comfortable', 'confort']
    lst_sunny = ['temperature', 'light', 'sunny']
    lst_number = ['1', '2', '3', '4', '5', '6', '7', '8']

    def __init__(self):
        pass
        # self.timeOfCreation = timeOfCreation

    def wantHome(self,msg):
        if presence(msg.lower(), self.lst_home):
            return True
        else:
            return False
	
    def wantHomeAndCity(self,msg):
        if(presence(msg.lower(), self.lst_home) and presence(msg.lower(), self.lst_city)):
            for k in range(0, len(msg.lower())):
                if(msg.lower()[k] == 'i'):
                    return msg[k+3::]
                elif(msg.lower()[k] == 'c'):
                    return msg[k+9::] 
        else:
            return False
           
    def wantSecure(self, msg):
        if(presence(msg.lower(), self.lst_secure)):
            return True
        else:
            return False
    
    def wantComfortable(self, msg):
        if(presence(msg.lower(), self.lst_comfortable)):
            return True
        else:
            return False
			
    def wantSunny(self, msg):
        if(presence(msg.lower(), self.lst_sunny)):
            return True
        else:
            return False
			
    def houseNumber(self, msg):
        if(presence(msg.lower(), self.lst_number)):
            return True
        else:
            return False
		
def presence(ch, L):
	Lch = []
	buf = ""
	for k in range(0, len(ch)):
		if(ch[k]==" "):
			Lch+=[buf]
			buf=""
		else:
			buf+=ch[k]
	Lch+=[buf]
	for i in L:
		for j in Lch:
			if(j==i):
				return True
	return False
            
