import pandas as pd
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

df = pd.read_csv("C:/Users/Syed/Desktop/ldds.csv")
test = pd.read_csv("C:/Users/Syed/Desktop/lddsp.csv")

X_test = test.iloc[:,2:26]
#X_test = df.iloc[:,2:26]
X_train = df.iloc[:,2:26]
Y_train= df.iloc[:,26:27]

# =============================================================================
#X_train, X_test, Y_train, Y_test = train_test_split(X_train, Y_train, test_size=0.2) #Splits data to 80% train 20% test
# =============================================================================

#from sklearn.linear_model import SGDClassifier
#clf= SGDClassifier(loss="hinge",penalty="l2", max_iter=5)
#clf.fit(X_train, Y_train)
#y_predict = clf_entropy.predict(X_test)


clf_entropy = DecisionTreeClassifier(criterion = "gini", random_state = 0, max_depth = 1000
                                     , min_samples_leaf=1) 
clf_entropy.fit(X_train, Y_train) # train the tree

y_predict = clf_entropy.predict(X_test) #Test it


#print("Accuracy is: ",accuracy_score(Y_test,y_predict)*100) #Compare it with actual results aka accuracy

#svm, regression 