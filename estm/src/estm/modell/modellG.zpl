# Ein LP für die Optimierung von Elternsprechtagswünschen
# Gerechte Verteilung der Termine
# Author Georg Dorndorf
# Version 01.03.2015

#Lehrer
set L  := {read "test3.dat" as "<2s>" match "^Lehrer:" comment "#"};
#Elter
set E := {read "test3.dat" as "<2s>" match "^Elter:" comment "#"};
#Termine
set T := {read "test3.dat" as "<2n>" match "^Termin:" comment "#"};

#Wünsche <Termin, Lehrer, Elter>
param w[L*E] := read "test3.dat" as "<2s,3s> 4n" match "^Wunsch:" comment "#" default 0;

#Zuweisung
set TxLxE := T * {<j,k> in L * E | w[j,k] > 0};

#Entscheidungsvariable
var x[TxLxE] binary ;
var y[E] real ;
var z[E] real ;
 
defnumb wun(j,k) := if w[j,k] != 0 then 1 else 0 end ;

#Kosten
minimize cost: sum <i,j,k> in TxLxE: ((wun(j,k) * -1) * (1/(abs(w[j,k]-i)+ 1))  *  x[i,j,k]);

subto a: forall <i> in T: forall <j> in L: sum <i,j,k> in TxLxE: x[i,j,k] <= 1;
subto b: forall <i> in T: forall <k> in E: sum <i,j,k> in TxLxE: x[i,j,k] <= 1;
subto c: forall <k> in E: forall <j> in L: sum <i,j,k> in TxLxE: x[i,j,k] <= 1;

#Steinbruch
#================================================================================================

#set TxLxE := {<i, j, k> in T * L * E | w[j,k] != 0};
#do forall <i> in L: forall <j> in E: print i , " ", j, " ", w[i,j];
#defnumb wunsch(i,j,k) :=  if <i,j,k> in W then 10 else -20 end ;
#defstrg wunschV(t,j,k):=  if <t,j,k> in W and wunsch(t,j,k) != 10 then 10 else 0 end ; 
#do forall <i,j,k> in TxLxE do print "Termin: ", i, " |", j, " |", k, " |", wunsch(i,j,k);
#maximize   cost: sum <i,j,k> in TxLxE: wunsch(i,j,k) * x[i,j,k];
#subto a: forall <i> in L: sum <i,j,k> in TxLxE: x[i, j, k] == 1;
#subto b: forall <j> in T: sum <i,j,k> in TxLxE: x[i, j, k] == 1;
#subto c: forall <k> in E: sum <i,j,k> in TxLxE: x[i, j, k] == 1;
#do print LxT;
#do print x;
#do print E;
#defnumb e(i,j,k) := if w[j,k] == i then -1 else 0 end ;
