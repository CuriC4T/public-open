import tensorflow as tf
import numpy as np
from pandas.io.parsers import read_csv

model = tf.global_variables_initializer()
data = read_csv('./price data.csv',sep=',') #csv 파일 위치

xy = np.array(data,dtype=np.float32) #데이터를 행렬 array로

x_data = xy[:,1:-1] #파이썬 슬라이싱 문법으로 날씨데이터 1열부터 마지막 열 전까지를 x_data로 함
y_data = xy[:,[-1]] #마지막 열 (가격 데이터)를 y_data로 함

X = tf.placeholder(tf.float32,shape =[None,4]) #플레이스홀더의 shape를 행:none(미지수) 열:4 로 생성
Y = tf.placeholder(tf.float32,shape =[None,1]) #플레이스홀더의 shape를 행:none(미지수) 열:1 로 생성

W = tf.Variable(tf.random_normal([4,1]),name="weight")
b = tf.Variable(tf.random_normal([1]),name="bias")

hypothesis = tf.matmul(X,W)+b #hypothesis = W*X + b

cost = tf.reduce_mean(tf.square(hypothesis - Y)) #hypothesis - Y를 제곱한 평균이 오차값 cost

optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.000005)
train = optimizer.minimize(cost)

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for step in range(100001):
    cost_,hypo_,_=sess.run([cost,hypothesis,train],feed_dict={X:x_data,Y:y_data})
    if step%500 ==0:
        print("#",step,"손실비용: ",cost_)
        print("- 배추 가격: ",hypo_[0])

saver = tf.train.Saver()
save_path=saver.save(sess,"./saveprice.cpkt")
print('학습 모델을 저장 했다!')

