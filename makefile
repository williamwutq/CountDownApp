# This is not intended to compile anything, but easy for lanuching the application


PROGRAM = java -cp src Main
SEPARATE = & disown

# Launch the app with no arguments (shows current time)
now:
	@$(PROGRAM) $(SEPARATE)

# New Year countdown (to Jan 1, 00:00:00 next year)
newyear:
	@$(PROGRAM) -dt $$(($$(date +%Y)+1))-01-01 -t 00:00:00 $(SEPARATE)

# Midnight countdown (to next midnight)
midnight:
	@$(PROGRAM) -n $(SEPARATE)

# Minute countdowns (relative)
timer1:
	@$(PROGRAM) -r -m 1 $(SEPARATE)
timer2:
	@$(PROGRAM) -r -m 2 $(SEPARATE)
timer3:
	@$(PROGRAM) -r -m 3 $(SEPARATE)
timer5:
	@$(PROGRAM) -r -m 5 $(SEPARATE)
timer10:
	@$(PROGRAM) -r -m 10 $(SEPARATE)
timer20:
	@$(PROGRAM) -r -m 20 $(SEPARATE)
timer25:
	@$(PROGRAM) -r -m 25 $(SEPARATE)
timer30:
	@$(PROGRAM) -r -m 30 $(SEPARATE)
timer45:
	@$(PROGRAM) -r -m 45 $(SEPARATE)
timer60:
	@$(PROGRAM) -r -m 60 $(SEPARATE)
timer90:
	@$(PROGRAM) -r -m 90 $(SEPARATE)
