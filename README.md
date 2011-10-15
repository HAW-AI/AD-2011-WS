Algorithmen und Datenstrukturen 2011 WS
=======================================

ADT
---
**Type**    	  	Permutation
**Import**   		int, String, Bool, Menge, Sequenz 
**Literals** 		idn (für jede Permutationsgruppe Sn)
 
Operations
---------
<table><tr>
<td>Permutation:</td>
<td>Sequenz<int> ---> Permutation</td>
<td>Erzeugt eine Permutation</td>
</tr><tr>
<td>sigma:</td>
<td>int x Permutation -/-> int</td>
<td>Gibt das gewählte Abbild</td>
</tr><tr>
<td>cycle:</td>
<td>Permutation x int -/-> Sequenz&lt;int></td>
<td>Gibt den gewählten Zyklus</td>
</tr><tr>
<td>allCycles:</td>
<td>Permutation ---> Menge&lt;Sequenz&lt;int>></td>
<td>Gibt alle Zyklen aus der Permutation</td>
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
</tr></table> 

Axioms
------
**σ: Permutation σ ∈ S1**
cycle(σ ,1) = fixedPoints(σ)
inverse(σ) = σ
inverse(σ) = compose(σ,σ)
compose(σ,inverse(σ)) = σ
equals(σ,σ) = true


**σ1,σ2, σ3 :Permutation σ1,σ2, σ3 ∈ Sn**
compose(σ1,compose(σ2,σ3)) = compose(compose(σ1,σ2),σ3)
equals(σ1,id) = true & equals(σ1,σ2) = false => fixedPoints(σ1) != fixedPoints(σ2)
inverse(inverse(σ3)) = σ3
equals(σ1,σ2) = equals(σ2,σ1)
equals(σ1,σ2) = equals(cycle(σ1), cycle(σ2))
equals(σ1,σ2) => equals(inverse(σ1), inverse(σ2))