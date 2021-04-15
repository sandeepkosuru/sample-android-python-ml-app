import pandas as pd
import pickle
from sklearn import linear_model

tableF = pd.read_excel('D:/Mini Projects/App_Py_Code/test.xlsx')
reg = linear_model.LinearRegression()
reg.fit(tableF[['S11','S12','S21','S22','S31']],tableF.S32)
#res = reg.predict([[float(sgpa1),float(sgpa2),float(sgpa3),float(sgpa4),float(sgpa5)]])
#print("Your NEXT SGPA May be:",res)


pickle.dump(reg,open('model.pkl','wb'))
