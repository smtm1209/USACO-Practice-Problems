#Sarkis Ter Martirosyan

union_init = lambda s: [i for i in range(s)]

def union_query(d, n):
	res = n
	while d[res] != res: res = d[res]
	m = 0
	while d[n] != n: m, d[n], n = d[n], res, m
	return res

def union_merge(d, x, y):
	x, y = union_query(d, x), union_query(d, y)
	if x == y: return -1
	d[x] = y
	return 1

if __name__ == '__main__':
	fin, fout = open('closing.in', 'r'), open('closing.out', 'w')
	N, M = [int(x) for x in next(fin).strip().split()]
	g, order = [tuple([int(x) for x in next(fin).strip().split()]) for i in range(M)], [int(next(fin).strip()) for i in range(N)]
	place = [0]*(N+1)
	for i in range(len(order)): place[order[i]] = i
	adj = {}
	for i in range(N+1): 
		adj[i] = set()
#	print(N, g, adj, order, place, adj)
	
	for i in range(M):
		if place[g[i][0]] > place[g[i][1]]: adj[g[i][1]].add(g[i][0])
		else: adj[g[i][0]].add(g[i][1])

#	print(N, g, adj, order, place, adj)

	par, comps, res = union_init(N+1), 0, [False]*N

	for i in range(N-1, -1, -1):
		u, comps = order[i], comps+1
		for v in adj[u]:
			if union_query(par, u) != union_query(par, v):
				union_merge(par, u, v)
				comps -= 1
		res[i] = comps <= 1

#	print(res)

	for b in res:
		if b: fout.write('YES\n')
		else: fout.write('NO\n')

