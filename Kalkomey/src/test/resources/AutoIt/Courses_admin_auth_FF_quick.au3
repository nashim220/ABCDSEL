
;$st = random(25000, 40000, 1) ; Sleep Time

;sleep($st)

sleep(5000)

;("data:, - Google Chrome","","10") ; this is the name of the window, according to AUTOIT v3 window info
;If WinExists("data:, - Google Chrome","") Then
;WinActivate("data:, - Google Chrome") ; set control to the window for proxy authentication

;WinWait("Authentication Required", "", $st)

If WinExists("Authentication Required","") Then
   WinActivate("Authentication Required","")
   Send("piyush{TAB}") ; send username and press TAB
   ;WinActivate("data:, - Google Chrome") ; again set control to our window, in case that we have clicked somewhere else
   Send("piyush{ENTER}") ; send the password and press enter
EndIf


