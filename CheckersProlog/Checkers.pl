%----------------------------------------------------------------------------------------
%----------------Names in estonian due to requirements at the time ----------------------
%----------------------------------------------------------------------------------------

:- module(iap142599, [iap142599/3]). 

leia_suund(1,1):- !.
leia_suund(2,-1).


iap142599(MinuNupp, Xin, Yin):-
	(((Xin=\=0 ; Yin=\=0), X is Xin, Y is Yin, ruut(X,Y,Tyyp)) ;
	ruut(X, Y, Tyyp)), 
	minu_nupp(MinuNupp, Tyyp), 
	leia_suund(MinuNupp, Suund),
	votmine(X, Y, Suund, X1, Y1, MinuNupp),
	!.
iap142599(MinuNupp, Xin, Yin):-
	(((Xin=\=0 ; Yin=\=0), X is Xin, Y is Yin, ruut(X,Y,Tyyp)) ;
	ruut(X, Y, Tyyp)), 
	minu_nupp(MinuNupp, Tyyp), 
	leia_suund(MinuNupp, Suund),
	kaimine(X, Y, Suund, X1, Y1),
	!.
iap142599(_, _, _).

minu_nupp(Varv, Varv).
minu_nupp(1, 10).
minu_nupp(2, 20).



kaigu_variandid(X, Y, Suund, X1, Y1):-
	ruut(X,Y,Nupp), minu_nupp(MinuNupp, Nupp), 
	votmine(X, Y, Suund, X1, Y1, MinuNupp),!.
kaigu_variandid(X, Y, Suund, X1, Y1):-
	kaimine(X, Y, Suund, X1, Y1),!.

%------------------------------------------------------------------------------------------
%----------------------------VOTMINE-------------------------------------------------------
%------------------------------------------------------------------------------------------	
votmine(X,Y,Suund,X1,Y1, MinuNupp):-
	kas_saab_votta(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp),
	vota(X,Y,Suund,X1,Y1,X2,Y2), !.

kas_saab_votta(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp):-  % Votmine edasi paremale
	X1 is X + Suund,
	Y1 is Y + 1,
	ruut(X1,Y1, Tyyp),
	not(minu_nupp(MinuNupp, Tyyp)),
	Tyyp =\= 0,
	X2 is X1 + Suund,
	Y2 is Y1 + 1,
	ruut(X2,Y2, 0).
kas_saab_votta(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp):-  % Votmine edasi vasakule
	X1 is X + Suund,
	Y1 is Y - 1,
	ruut(X1,Y1, Tyyp),
	not(minu_nupp(MinuNupp, Tyyp)),
	Tyyp =\= 0,
	X2 is X1 + Suund,
	Y2 is Y1 - 1,
	ruut(X2,Y2, 0).
kas_saab_votta(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp):-  % Votmine tagasi paremale
	X1 is X + Suund * -1,
	Y1 is Y + 1,
	ruut(X1,Y1, Tyyp),
	not(minu_nupp(MinuNupp, Tyyp)),
	Tyyp =\= 0,
	X2 is X1 + Suund * -1,
	Y2 is Y1 + 1,
	ruut(X2,Y2, 0).
kas_saab_votta(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp):-  % Votmine tagasi vasakule
	X1 is X + Suund * -1,
	Y1 is Y - 1,
	ruut(X1,Y1, Tyyp),
	not(minu_nupp(MinuNupp, Tyyp)),
	Tyyp =\= 0,
	X2 is X1 + Suund * -1,
	Y2 is Y1 - 1,
	ruut(X2,Y2, 0).

kas_saab_votta(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp) :- % tamm
	ruut(X, Y, Tyyp),
	tamm(Tyyp),										
	diagonaalid(X, Y, X1, Y1),
	ruut(X1,Y1, SihtTyyp),
	not(minu_nupp(MinuNupp, SihtTyyp)),
	SihtTyyp =\= 0,
	kas_saab_votta_nuppu(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp).
		
%-----------------------------------------------------------------------------------------
%------------------------------- KAIMINE -------------------------------------------------
%-----------------------------------------------------------------------------------------
kaimine(X,Y,Suund,X1,Y1):-
	kas_naaber_vaba(X,Y,Suund,X1,Y1),
	tee_kaik(X,Y,X1,Y1), !.

kas_naaber_vaba(X,Y,Suund,X1,Y1):-
	X1 is X +Suund,
	Y1 is Y + 1,
	ruut(X1,Y1, 0).
	
kas_naaber_vaba(X,Y,Suund,X1,Y1):-
	X1 is X +Suund,
	Y1 is Y -1,
	ruut(X1,Y1, 0).
	
kas_naaber_vaba(X,Y,Suund,X1,Y1) :-
	ruut(X, Y, Tyyp),
	tamm(Tyyp),
	X1 is X - Suund,
	Y1 is Y + 1,
	ruut(X1,Y1, 0).
	
kas_naaber_vaba(X,Y,Suund,X1,Y1) :-
	ruut(X, Y, Tyyp),
	tamm(Tyyp),
	X1 is X - Suund,
	Y1 is Y - 1,
	ruut(X1,Y1, 0).
	
%----------------------------------------------------------------------------------------
%------------------------------------TAMMI ABIPREDIKAADID--------------------------------
%----------------------------------------------------------------------------------------
kas_saab_tammiks(1, 8, 10).
kas_saab_tammiks(2, 1, 20).
kas_saab_tammiks(Tyyp, _, Tyyp).

tamm(10).
tamm(20).
	
diagonaalid(X, Y, X1, Y1) :-
	between(1, 16, Loendur), 
	(Loendur =< 8 ->
	Y1 is Loendur,
	X1 is Y1 + X - Y
;	Y1 is Loendur - 8,
	X1 is X + Y - Y1),	
	X1 >= 1,
	X1 =< 8.
diagonaalid(X, Y, -1, -1).

kas_saab_votta_nuppu(X,Y,Suund,X1,Y1,X2,Y2, MinuNupp) :-
	tee_vaba(X, Y, X1, Y1), 
	taga_on_ruumi(X, Y, X1, Y1, X2, Y2).	

tee_vaba(X, Y, X1, Y1) :-
	diagonaalid(X, Y, Xi, Yi),
	(Xi == -1 ;
		(peaks_tyhi_olema(X, Y, X1, Y1, Xi, Yi) ->
			(not(ruut(Xi, Yi, 0)) ->
			!, fail;
			fail);
		fail)).

taga_on_ruumi(X, Y, X1, Y1, Xt, Yt) :- 
	VaheX is X1 - X,
	VaheY is Y1 - Y,
	SignX is sign(VaheX),
	SignY is sign(VaheY),
	Xt is X1 + SignX,
	Yt is Y1 + SignY,
	ruut(Xt, Yt, 0).
		
peaks_tyhi_olema(X, Y, X1, Y1, Xi, Yi) :-
	kaugus(X, Y, Xi, Yi, KaugusPunktist),
	kaugus(X, Y, X1, Y1, KaugusSihist),
	KaugusPunktist > 0,
	KaugusPunktist < KaugusSihist,
	VaheX1 is X1 - X,
	VaheXi is Xi - X,
	sign(VaheX1) =:= sign(VaheXi),
	VaheY1 is Y1 - Y,
	VaheYi is Yi - Y,
	sign(VaheY1) =:= sign(VaheYi).
	
kaugus(X1, Y1, X2, Y2, Kaugus) :-
	((VahX is X2 - X1, VahX < 0, VaheX is VahX * -1);(VaheX is X2 - X1)),
	((VahY is Y2 - Y1, VahY < 0, VaheY is VahY * -1);(VaheY is Y2 - Y1)),
	Kaugus is max(VaheX, VaheY).	
%-----------------------------------------------------------------------------------------------	
%-------------------------------------LOPLIKUD--------------------------------------------------
%-----------------------------------------------------------------------------------------------	 
tee_kaik(X, Y, X1, Y1) :-
	retract(ruut(X, Y, Tyyp)),
	retract(ruut(X1, Y1, _)),
	kas_saab_tammiks(Tyyp, X1, UusTyyp),
	assert(ruut(X1, Y1, UusTyyp)),
	assert(ruut(X, Y, 0)).
	
vota(X, Y, Suund, X1, Y1, X2, Y2) :- 
	retract(ruut(X, Y, Tyyp)),
	assert(ruut(X, Y, 0)),
	retract(ruut(X2, Y2, _)),
	kas_saab_tammiks(Tyyp, X2, UusTyyp),
	assert(ruut(X2, Y2, UusTyyp)),
	retract(ruut(X1, Y1, _)),
	assert(ruut(X1, Y1, 0)).	