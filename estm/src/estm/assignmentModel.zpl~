#Lehrer
#set L  := {"A", "B", "C"};
set L  := {read "test.dat" as "<2s>" match "^Lehrer:"};
#Termine
#set T := {"a", "b", "c"};
set T := {read "test.dat" as "2s" match "^Termin:"};
#Zuweisung
set LxT := L cross T;

var x[LxT] binary ;
#defnumb c(i, j) := random(1, 10);
param c[<i,j> in LxT] := floor(random(1, 10));
do forall <i,j> in LxT do print i, " ", j, " ", c[i,j];

minimize cost: sum <i,j> in LxT : c[i,j] * x[i, j];

subto a: forall <i> in L: sum <i,j> in LxT: x[i, j] == 1;
subto b: forall <j> in T: sum <i,j> in LxT: x[i, j] == 1;

#do print LxT;
#do print x;
