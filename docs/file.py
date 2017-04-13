name = []

filename = input("Enter Class Name : ")

f = open(filename+".java",'w')
f.write("//mail me at mr.niaz@live.com for any kind of bug\n\n")
print("Enter Variables : \n(Apatoto 4ta diye dekhen & just variable er name likhe likhe enter den without datatype)\n")
f.write("public class "+filename+"\n{\n")

for i in range(0,4):
	name.append(str(input()))
for i in name:
	f.write("\tprivate String "+i+";\n")
	
f.write("\n")
	
for i in name:
	statement = "\tpublic String get"+i+"()\n\t{\n\t\treturn "+i+";\n\t}\n\n"
	f.write(statement)
for i in name:
	statement = "\tpublic void set"+i+"(String "+i+")\n\t{\n\t\tthis."+i+" = "+i+";\n\t}\n\n"
	f.write(statement)
	
f.write("\tpublic static void main(String[] args)\n\t{\n\n\t}")
f.write("\n}")
print("\n \nYour java getter setter methods has been created.\nLook in the directory  where this exe file is located.\nFile name : "+filename+".java")

f.close()
