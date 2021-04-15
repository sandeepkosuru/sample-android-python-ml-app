import numpy as np
from flask import Flask,request,jsonify
import pickle
import requests,ssl
from flask_cors import CORS
app=Flask(__name__)
model=pickle.load(open('model.pkl','rb'))
CORS(app)

@app.route('/predict',methods=['POST'])
def predict():
    int_features=[]
    print(request)
    sgpa1=request.json['S11']
    int_features.append(float(sgpa1))
    sgpa2=request.json['S12']
    int_features.append(float(sgpa2))
    sgpa3=request.json['S21']
    int_features.append(float(sgpa3))
    sgpa4=request.json['S22']
    int_features.append(float(sgpa4))
    sgpa5=request.json['S31']
    int_features.append(float(sgpa5))
    final_features=[np.array(int_features)]
    prediction=model.predict(final_features)
    #output=int(prediction)
    return {"MESSAGE":round(float(prediction),2)}
    
if __name__=="__main__":
    app.run(debug=True)
