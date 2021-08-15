# Genetic Algorithm
A genetic algorithm that finds the solution of correct column ordering for a "shredded" text file.
The solution is the final permutation of the genome that shows the correct order of columns to the original readable text.

#### Goal 
`Build a genetic algorithm that finds the fittest genome to solve the shredded documents original text.`

### Parameters
```java
//params to adjust the GA 
int populationSize = 0;
int maxGenerations = 0;
float crossOverRate = 0f;
float mutationRate = 0f;
float elitePer = 0f;
float tournamentSizePer = 0f;
long seed = 0;
```
### Experimental Setup
<strong>To reproduce similar results 👇</strong>
<li>The population will be set to <strong>500</strong>. 
<li>The max generations are set to <strong>30</strong>.
<li>The experiment will be testing <strong>shredded document 3</strong>. 
<li>The fitness of the correct solution for document 3 is 743.

### Experimental Results
y-axis is the fitness of the fittest genome in the pool.



### Example Output
```
----------[MENU]----------
0: End program
1: DOC 1
2: DOC 2
3: DOC 3

Enter option 0 to 3:
1

-------[Document 1]-------
(1% is 1) (10% is 10) (100% is 100) etc...

Please build your GA below.

Population Size: 
1000
Max Generations: 
20
% CrossOver Rate: 
100
% Mutation Rate: 
10
% of Elites
10
% of Tournament Size: 
5
Seed: 
123

----------[GA]-----------
Seed:             123
Population Size:  1000
Max Generations:  20
Crossover Rate:   1.0
Mutation Rate:    0.1
Number of Elites: 100
Tournament Size:  50

--------[Results]--------
Generation: 1 avg: 156.275 best: 154.209
Generation: 2 avg: 155.378 best: 153.057
Generation: 3 avg: 154.640 best: 152.844
Generation: 4 avg: 153.969 best: 151.231
Generation: 5 avg: 153.308 best: 150.556
Generation: 6 avg: 152.746 best: 149.564
Generation: 7 avg: 152.356 best: 149.564
Generation: 8 avg: 151.946 best: 149.564
Generation: 9 avg: 151.555 best: 149.150
Generation: 10 avg: 151.089 best: 149.150
Generation: 11 avg: 150.420 best: 149.150
Generation: 12 avg: 149.372 best: 149.150
Generation: 13 avg: 149.320 best: 149.150
Generation: 14 avg: 149.330 best: 149.150
Generation: 15 avg: 149.338 best: 149.150
Generation: 16 avg: 149.349 best: 149.150
Generation: 17 avg: 149.310 best: 149.150
Generation: 18 avg: 149.355 best: 149.150
Generation: 19 avg: 149.331 best: 149.150
Generation: 20 avg: 149.346 best: 149.150

---------[Output]---------
Hey, look at me
. It's your fau
lt, it's everyo
ne's fault, who
 cares. Are you
 up for this? A
re you? Look, I
 just need to k
now, cause the 
city is flying.
 Okay, look, th
e city is flyin
g, we're fighti
ng an army of r
obots, and I ha
ve a bow and ar
row. None of th
is makes sense.
 But I'm going 
back out there 
because it's my
 job. Okay? And
 I can't do my 
job and babysit
. It doesn't ma
tter what you d
id, or what you
 were. If you g
o out there, yo
u fight, and yo
u fight to kill
. Stay in here,
 you're good, I
'll send your b
rother to come 
find you, but i
f you step out 
that door, you 
are an Avenger.
 Alright, good 
chat. Yeah, the
 city is flying
.              


--------[Solution]--------
Solution Perm: [2, 0, 7, 12, 4, 1, 11, 5, 14, 3, 8, 13, 10, 6, 9]
Fitness: 149.150

```