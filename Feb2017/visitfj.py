#!/usr/bin/python3
import bisect
from collections import deque
import sys
import heapq

dr = [0, 1, 2, 3, 2, 1, 0, -1, -2, -3, -2, -1, -1, 1, 0, 0]
dc = [3, 2, 1, 0, -1, -2, -3, -2, -1, 0, 1, 2, 0, 0, -1, 1]

if __name__ == '__main__':
	
	fin = open('visitfj.in', 'rb')
	fout = open('visitfj.out', 'w')
	N, T = [int(x) for x in next(fin).split()]
	weights = []
	for line in fin:
		weights.append([int(x) for x in line.split()])
#	print(N, T, weights)
	D = [[0x7FFFFFFF for j in range(N)] for i in range(N)]
#	print(D)
	D[0][0] = 0
#	print(D)	
	pq = [(0, 0)]
	heapq.heapify(pq)

	ret = 0x7FFFFFFF;
	
	while len(pq) > 0:
		node = heapq.heappop(pq)
		d = -node[0]
		r = node[1] // N
		c = node[1] % N	
		if d != D[r][c]: continue
		
		dist = abs(N - 1 - r) + abs(N - 1 - c)
		if dist <= 2: ret = min(ret, d + dist * T)
		for i in range(len(dr)):
			nr = r + dr[i]
			nc = c + dc[i]
			if nr < 0 or nr >= N or nc < 0 or nc >= N or D[nr][nc] < d + weights[nr][nc] + 3 * T:
				continue
			D[nr][nc] = d + weights[nr][nc] + 3 * T
			heapq.heappush(pq, (-D[nr][nc], nr * N + nc))

	print(ret)
	fout.write('{:d}\n'.format(ret))
	fout.close()
			
	

	"""
	start = (0, 0, 0, 0) #(time, row, column, # of steps)
	pq = [start]
	heapq.heapify(pq)
	while len(pq) > 0:
		node = heapq.heappop(pq)
#		keys.pop(0)
#		if node[2] % 3 == 0 and node[2] != 0:
#			node = (node[0], node[1], node[2], node[3] + weights[node[1]][node[0]])
#		print(node, end=' ')
		if node[1] == N - 1 and node[2] == N - 1:
#			ans.append(node[3])
			fout.write('{}\n'.format(node[0]))
			print('{}'.format(node[0]))
			fout.close()
			sys.exit(0)	
		ext = []
		if node[1] > 0:	ext.append((node[0] + T, node[1] - 1, node[2], node[3] + 1)) 
		if node[1] < N-1: ext.append((node[0] + T, node[1] + 1, node[2], node[3] + 1))
		if node[2] > 0: ext.append((node[0] + T, node[1], node[2] - 1, node[3] + 1))
		if node[2] < N-1: ext.append((node[0] + T, node[1], node[2] + 1, node[3] + 1))
#		print(ext)
		for n in ext:
			x = n
			if n[3] % 3 == 0 and n[3] != 0:
				x = (n[0] + weights[n[2]][n[1]], n[1], n[2], n[3])
#			key = x[3]
#			i = bisect.bisect_left(keys, key)
#			keys.insert(i, key)
			heapq.heappush(pq, x)
			
		"""
