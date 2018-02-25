#! /bin/python3

import requests
import time

import json

ADDR = "52.14.216.215"
PORT = "8080"
PATH = "/rest"

def client_send():
	info = {
		"usecase": 0,
		"city": "st. gallen"
		}
	print(info)
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	# ~ parsed_json = json.loads(res.text)
	# ~ addr = "http://"+ADDR+":"+PORT+PATH
	# ~ res = requests.get(addr)
	print(res.text)
	return 0

def uc1_send():
	info = {}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	
	print(res.text)
	return 0
	
while(1):
	client_send()
	time.sleep(2)


