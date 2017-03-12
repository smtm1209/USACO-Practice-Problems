#!/usr/bin/python3

if __name__ == '__main__':
	fin, fout = open('nocross.in', 'r'), open('nocross.out', 'w')
	N = eval(next(fin))
	S, T = [0]*N, [0]*N
	for i in range(N):
		S[i] = eval(next(fin))
	for i in range(N):
		T[i] = eval(next(fin))
#	print(S, T)	
	A = [[0 for i in range(N)] for i in range(N)]
	A[0][0] = int(abs(S[0] -T[0]) <= 4)
	for i in range(1, N):
		A[i][0] = max(A[i-1][0], int(abs(S[i]-T[0]) <= 4))
	for i in range(1, N):
		A[0][i] = max(A[0][i-1], int(abs(S[0]-T[i]) <= 4))
#	print(A)
	for i in range(1, N):
		for j in range(1, N):
			A[i][j] = max(max(A[i-1][j], A[i][j-1]), A[i-1][j-1]+int(abs(S[i]-T[j])<=4))
#	print(A[N-1][N-1])
	fout.write('{:d}\n'.format(A[N-1][N-1]))
	fout.close()
