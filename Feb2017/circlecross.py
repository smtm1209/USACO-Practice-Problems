
if __name__ == '__main__':
	
	i =	1
	intersections = 0
	S = set()
	E = {}
	seen = set()
	fin = open('circlecross.in', 'rb')
	n = int(fin.readline())
	while i <= 2 * n:
		j = int(fin.readline())
		#print(j)
		if j not in seen:
			seen.add(j)
			E[j] = i
			S.add(i)
			#print(S, E)
		else:
			#print(S, E, j, end=' ')
			#print({x for x in S if x > E[j]})
			intersections += len({x for x in S if x > E[j]})
			if E[j] in S: S.remove(E[j])
		
		i += 1
	fout = open('circlecross.out', 'w')
	fout.write('{:d}\n'.format(intersections))
	fout.close()
