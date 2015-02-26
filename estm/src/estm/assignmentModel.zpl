#Lehrer
#set L  := {"A", "B", "C"};
set L  := {read "test.dat" as "<2s>" match "^Lehrer:" comment "#"};

#Elter
set E := {read "test.dat" as "<2s>" match "^Elter" comment "#"};
do print E;
#Termine
#set T := {"a", "b", "c"};
set T := {read "test.dat" as "<2s>" match "^Termin:" comment "#"};

#Zuweisung
set LxT := L cross T;

set LxTxE := L * T * E;
#Entscheidungsvariable
#var x[LxT] binary ;
var x[LxTxE] binary ;
#defnumb c(i, j) := random(1, 10);
#param c[<i,j> in LxT] := floor(random(1, 10));
param c[<i,j,k> in LxTxE] := floor(random(1, 10));
#do forall <i,j> in LxT do print i, " ", j, " ", c[i,j];
do forall <i,j,k> in LxTxE do print i, " ", j, " ", k, " ", c[i,j,k];

#minimize cost: sum <i,j> in LxT : c[i,j] * x[i, j];
minimize cost: sum <i,j,k> in LxTxE : c[i,j,k] * x[i, j,k];

# jeder Lehrer = 1 Termin jeder Termin = 1 Lehrer
#subto a: forall <i> in L: sum <i,j> in LxT: x[i, j] == 1;
#subto b: forall <j> in T: sum <i,j> in LxT: x[i, j] == 1;

subto a: forall <i> in L: sum <i,j,k> in LxTxE: x[i, j, k] == 1;
subto b: forall <j> in T: sum <i,j,k> in LxTxE: x[i, j, k] == 1;
subto c: forall <k> in E: sum <i,j,k> in LxTxE: x[i, j, k] == 1;
#do print LxT;
#do print x;
