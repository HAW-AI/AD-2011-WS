Algorithmen und Datenstrukturen 2011 WS
=======================================

ADT
---
**Type**       	Permutation

**Import**   		int, String, Bool, Menge, Sequenz 

**Literals** 		idn (für jede Permutationsgruppe Sn)

 
Operations
---------
<table><tr>
<td>Permutation:</td>
<td>Sequenz&lt;int> ---> Permutation</td>
<td>Erzeugt eine Permutation</td>
</tr><tr>
<td><td>Sequenz&lt;Sequenz&lt;int>> ---> Permutation</td></td>
</tr><tr>
<td><td>String -/-> Permutation</td></td>
</tr><tr>
<td>getPermElement:</td>
<td>int x Permutation -/-> int</td>
<td>Gibt das gewählte Abbild</td>
</tr><tr>
<td>cycle:</td>
<td>Permutation x int -/-> Permutation></td>
<td>Gibt den gewählten Zyklus</td>
</tr><tr>
<td>allCycles:</td>
<td>Permutation ---> Menge&lt;Sequenz&lt;int>></td>
<td>Gibt alle Zyklen aus der Permutation</td>
</tr><tr>
<td>cycleType:</td>
<td>Permutation ---> Hashtabelle&lt;int,int></td>
<td>Gibt den Zyklentyp der Permutation</td>
</tr><tr>
<td>fixedPoints:</td>
<td>Permutation ---> Menge&lt;Sequenz&lt;int>></td>
<td>Gibt alle Fixpunkt aus der Permutation</td>
</tr><tr>
<td>inverse:</td>
<td>Permutation ---> Permutation	</td>
<td>Gibt die invertierte Permutation</td>
</tr><tr>
<td>
toString:</td>
<td>Permutation ---> String</td>
<td>Darstellung als String</td>
</tr><tr>
<td>toCycleNotationString:</td>
<td>Permutation ---> String</td>
<td>Darstellung der Zyklen als String</td>
</tr><tr>
<td>toCycleTypeString:</td>
<td>Permutation ---> String</td>
<td>Darstellung des Zyklentyps als String</td>
</tr><tr>
<td>equals:</td>
<td>Permutation x Permutation ---> Bool</td>
<td>Prüft strukturelle Gleichheit</td>
</tr><tr>
<td>compose:</td>
<td>Permutation x Permutation -/-> Permutation</td>
<td>Gibt die Komposition der beiden Permutation wieder</td>
</tr><tr>
<td>permutationClass:</td>
<td>Permutation ---> int</td>
<td>Gibt die Anzahl der Elemente der Permutation</td>
</tr><tr>
<td>permPower:</td>
<td>Permutation x int ---> Permutation</td>
<td>Gibt die Permutation^n an</td>
</tr><tr>
<td>order:</td>
<td>Permutation ---> int</td>
<td>Gibt die Ordnung der Permutation an</td>
</tr><tr>
<td>id:</td>
<td>Permutation ---> Permutation</td>
<td>Gibt die Id der Permutation an</td>
</tr><tr>
<td>toTranspositions:</td>
<td>Permutation -/-> List<Permutation></td>
<td>Wandelt die Permutation in eine Transpositionsdarstellung um</td>
</tr><tr>
<td>toTranspositionString:</td>
<td>Permutation ---> String</td>
<td>Wandelt die Permutation in eine Transpositionsdarstellung in Stringform um</td>
</tr><tr>
<td>Sign:</td>
<td>Permutation ---> [-1; 1]</td>
<td>Gibt an ob die Permutation even(1) oder odd(-1) ist.</td>
</tr><tr>
<td>rank:</td>
<td>Permutation ---> int</td>
<td>Gibt den Rank der Permutation an.</td>
</tr><tr>
<td>rankToPerm:</td>
<td>Permutation x int ---> Permutation</td>
<td>Gibt die Permutation für den gewählten Rank</td>
</tr></table> 

Axioms
=====
**σ: Permutation σ ∈ S1**

cycle(σ ,1) = fixedPoints(σ)

inverse(σ) = σ

inverse(σ) = compose(σ,σ)

compose(σ,inverse(σ)) = σ

equals(σ,σ) = true


**σ1,σ2,σ3,id :Permutation σ1,σ2,σ3,id ∈ Sn**

**n : Integer n ∈N && n!=0**

id(σ1) = id;

id(id) = id;

compose(σ1,compose(σ2,σ3)) = compose(compose(σ1,σ2),σ3)

!equals(id, σ1) => |fixedPoints(σ1)| < n

inverse(inverse(σ3)) = σ3

equals(σ1,σ2) = equals(σ2,σ1)

equals(σ1,σ2) = equals(cycle(σ1), cycle(σ2))

equals(σ1,σ2) => equals(inverse(σ1), inverse(σ2))

permPower(σ1,0) = id

permPower(σ1,1) = σ1

permPower(σ1,-1) = inverse(σ1)

permPower(σ1, order(σ1)) = id

permPower(σ1,-n) = permPower(inverse(σ1), n)

cycleType(id) = [1^permutationClass(id)]
