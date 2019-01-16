import re
import json
import codecs
from sets import Set

# get files names
list = []
infile = open('lista_plikow.txt', 'r')

for line in infile:
    line = line.replace('\r\n', '').replace('\n', '')
    list.append(line)
	
infile.close()

# get only stops
stopsSet = Set([])
for file in list:
	infile = open('XML-rozkladyjazdy/'+ file, 'r')

	for line in infile:
		line = line.strip()
		if re.match(r'[ \t]*<przystanek id*', line):
			stopsSet.add(line)
			
	infile.close()

# parse Stops to HUMAN format 
mapped = []	
for line in stopsSet:
	line = line.decode('iso-8859-2').encode('utf8')
	str1 = re.search('id=".*?"', line).group(0).replace('id=', '').replace('"', '')
	str2 = re.search('nazwa=".*?"', line).group(0).replace('nazwa=', '').replace('"', '')

	mapped.append(str1 + ";" + str2)
	
#print mapped
fo = codecs.open("mappedStopIdToName.txt", "w")
fo.write("\n".join(mapped))
