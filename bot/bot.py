#!/usr/bin/env python3
import urllib
from requests import get
from datetime import datetime, timedelta
from dateutil import tz
from textblob import TextBlob
import time
import logging
import json
import requests

import dbClass as dbClass

# constants
LOOPTIMEOUT = 1 # in seconds
# ~ URL_BOT = "https://api.telegram.org/bot433042847:AAGUrcfu9FPgwd942dvFUiidG45FuFzoRpg/"
URL_BOT = "https://api.telegram.org/bot532946635:AAEpG1sPQUajjxtduexcaQHHu9FuClKxhuY/"
URL_API = "TODO"
ADDR = "52.14.216.215"
PORT = "8080"
PATH = "/rest/request"
URL_HOME = URL_API + "/events"
URL_HOME_LIST = URL_HOME + "/events?sort=-_time_start"


#
# Solve the use cases
#
def uc0_send(city):
	print("use case 0")
	info = {
		"usecase": 0,
		"city": city
		}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	print("======="+res.text)
	parsed_json = json.loads(res.text)
	# ~ matrix = np.column_stack((parsed_json["street"], parsed_json["site"]))
	# ~ print(matrix)
	return(parsed_json["streets"], parsed_json["urls"])
	
def uc1_send(addr): # Confort
	print("use case 1")
	info = {
		"usecase": 1,
		"house": addr
		}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	parsed_json = json.loads(res.text)
	humidity = parsed_json["humidity"]     #25
	vibration = parsed_json["vibration"]   #13,5
	noise = parsed_json["noise"]           #12
	if(humidity<30 and vibration<25 and noise<25):
		return("This place seems really comfortable (Humidity: "+str(humidity)+"%, Vibrations: "+str(vibration)+", noise: "+str(noise)+"dB)")
	elif(humidity>30):
		return("This place is a bit more humid than the average but it is still a really nice place to live (Humidity: "+str(humidity)+"%, Vibrations: "+str(vibration)+", noise: "+str(noise)+"dB)")
	elif(vibration>25):
		return("This place shakes a little more than the average but it is still a really nice place to live (Humidity: "+str(humidity)+"%, Vibrations: "+str(vibration)+", noise: "+str(noise)+"dB)")
	elif(noise>25):
		return("This place is a bit more noisy than the average but it is still a really nice place to live (Humidity: "+str(humidity)+"%, Vibrations: "+str(vibration)+", noise: "+str(noise)+"dB)")
	else:
		return -1
	
def uc2_send(addr): #Sunny
	info = {
		"usecase": 0,
		"city": city
		}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	parsed_json = json.loads(res.text)
	temperature = parsed_json["temperature"]     
	light = parsed_json["light"]  
	if(temperature>17 and light>12):
		return("This place seems really comfortable (Temperature: "+str(temperature)+"%, Light: "+str(light))
	elif(temperature<17):
		return("This place seems a bit cold but it is still a really nice place to live (Temperature: "+str(temperature)+"%, Light: "+str(light))
	elif(light<25):
		return("This place seems a bit dark but it is still a really nice place to live (Temperature: "+str(temperature)+"%, Light: "+str(light))
	else:
		return -1

#
# Functions to Telegram API
#
def get_updates(offset=None):
    url = URL_BOT + "getUpdates?timeout=10"
    if offset:
        url += "&offset={}".format(offset)
    return get(url).json()

def get_last_update_id(updates):
    update_ids=[]
    for update in updates["result"]:
        update_ids.append(int(update["update_id"]))
    return max(update_ids)

# ~ def receive_input_string():
    # ~ send_message("TODO This function is not yet implemented", chat)


def send_message(text, chat_id):
    # text = urllib.parse.quote_plus(text)
    url = URL_BOT + "sendMessage?parse_mode=markdown&text={}&chat_id={}".format(text, chat_id)
    get(url)

# ~ def get_home(indexDevice):
    # ~ resp = get(URL_HOME + indexDevice).json()
    # ~ if "_status" in resp:
        # ~ return None
    # ~ else:
        # ~ return resp

# ~ def help(chat_id):
    # ~ HELP_TEXT = """Help!"""
    # ~ send_message(HELP_TEXT, chat_id)

# ~ def initialize_prop_lists():
    # ~ lst_home = ['home','homes','appart','appartment','apt','house','room','Zimmer','Wohnung']


def main():
    logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',level=logging.INFO)
    last_update_id = None
    firstTime = True
    id_array = []
    db = dbClass.dbClassObj() # TODO add time of creation
    current_addr = ""
    street = []
    site = []
    while True:
        # read all new messages from Telegram bot
        updates=get_updates(last_update_id)
        if ("result" in updates) and (len(updates["result"]) > 0):
            last_update_id = get_last_update_id(updates)+1
        else:
            continue
        print(updates)
        # discard the first batch of valid updates to not spam users from old messages
        if firstTime:
            firstTime = False
            continue
        # for every new /event chat message, call send events
        for r in updates["result"]:
            print(r)
            if "text" in r["message"]:
                msg = r["message"]["text"].lower()
                chat = r["message"]["chat"]["id"]
                id_array.append(chat)
                city=db.wantHomeAndCity(msg)
                if(city!=False):
                    (street, site) = uc0_send(city)
                    send_message("Here are some alvailable properties near"+city+":", chat)
                    if(len(street)>5):
                        m = 5
                    else:
                        m = len(street)
                    for k in range(0, m):
                        send_message(str(k)+" "+str(street[k])+": "+str(site[k]) , chat)
                    send_message("What is the number of the place number are you interrested in:", chat)
                city=db.wantCity(msg)
                if(city!=False):
                    (street, site) = uc0_send(city)
                    send_message("Here are some alvailable properties near"+city+":", chat)
                    if(len(street)>5):
                        m = 5
                    else:
                        m = len(street)
                    for k in range(0, m):
                        send_message(str(k)+" "+str(street[k])+": "+str(site[k]) , chat)
                    send_message("What is the number of the place number are you interrested in:", chat)
                elif db.wantHome(msg):
                    send_message("In which city would you like to live ?", chat)
                elif db.wantSecure(msg):
                    send_message("The criminality is under 5%, it's a quietly safe neighborhood.", chat)
                elif db.wantComfortable(msg):
                     re = uc1_send(current_addr)
                     send_message(re, chat)
                elif db.wantSunny(msg):
                     re = uc2_send(current_addr)
                     send_message(re, chat)
                elif db.houseNumber(msg):
                     current_addr = street[int(msg)]
                     send_message("Good choice, that's a really nice place", chat)
                     send_message("What would you like to know mor about this place", chat)
                # ~ elif db.wantZIP(msg):
                    # ~ send_message("recognized zip", chat)
                # ~ elif db.wantStreet(msg):
                    # ~ send_message("recognized street", chat)
        # pause the loop
        time.sleep(LOOPTIMEOUT)

if __name__ == '__main__':
    main()
