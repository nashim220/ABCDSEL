Sleep(15000)

If WinExists("https://admintools-qa.kalkomey.com/","") Then
   WinActivate("https://admintools-qa.kalkomey.com/","")
   Send("piyush{TAB}") ; send username and press TAB
   ;WinActivate("data:, - Google Chrome") ; again set control to our window, in case that we have clicked somewhere else
   Send("piyush{ENTER}") ; send the password and press enter
EndIf
   ;Send("piyush{TAB}")s
  ;Send("piyush{ENTER}")

;WinActivate("data:, - Google Chrome") ; set control to the window for proxy authentication
;Send("piyush{TAB}") ; send username and press TAB
;WinActivate("data:, - Google Chrome") ; again set control to our window, in case that we have clicked somewhere else
;Send("piyush{ENTER}") ; send the password and press enter



