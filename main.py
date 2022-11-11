import csv, requests, json, ast

#user object
class UserData():
    
    def get_email(self):
        return self.email

    def get_first_name(self):
       return self.first_name

    def get_last_name(self):
       return self.last_name
    
    def get_uid(self):
        return self.uid

    def __init__(self, first_name, last_name,email,uid):
       self.first_name = first_name
       self.last_name = last_name
       self.email=email
       self.uid=uid

# initial read and merge
def read_files_initial_merge(file1,file2):

    reader = csv.reader(open(file2, 'r'))
    data = {}
    for row in reader:
        k, first_name,last_name = row
        data[k] = UserData(first_name,last_name,'',k)

    reader = csv.reader(open(file1, 'r'))

    for row in reader:
        k, email = row
        data[k].email=email
    return data


# MAIN BODY
data =  read_files_initial_merge('FileA.csv','FileB.csv')
headers = {'Content-Type':'application/x-www-form-urlencoded','Accept': 'application/json'}
    
f = open('merged_file.csv', 'w')

# for each object service call
for i in data:
    data_to_send = 'aid=o1sRRZSLlw&api_token=xeYjNEhmutkgkqCZyhBn6DErVntAKDx30FqFOS6D&email='+data[i].email+'&limit=1&offset=0'
    url = 'https://sandbox.piano.io/api/v3/publisher/user/search'
    
    response = requests.post(url, data_to_send, headers=headers)
    
    # if invocation succesful performs checks
    if response.status_code == 200:
        ret= response.json()

        user_data = str(ret['users'])[1:-1]
        if len(user_data) > 0:
            user_data = user_data.replace("\'", "\"")
            result = ast.literal_eval(user_data)
            uid = result['uid']
            data[i].uid=uid
            #print(ret)
          
    merged_file =f.writelines(data[i].uid+','+data[i].email+','+data[i].first_name+','+data[i].last_name+'\n')
    print (data[i].uid+','+data[i].email+','+data[i].first_name+','+data[i].last_name+'\n')
       



  