@echo off
ECHO Deseja restaurar as bases a seguir?
ECHO gestao_atividades

pause

dropdb -h localhost -U postgres -w -p 5432 gestao_atividades
ECHO Tentativa de drop efetuada, se teve sucesso clique para continuar.
pause
createdb -E UTF8 -O pod1 -h localhost -p 5432 -U postgres -w gestao_atividades
pg_restore -U postgres -v -Fc -n public -d gestao_atividades gestao_atividades.backup

pause