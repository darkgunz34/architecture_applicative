git pull https://github.com/darkgunz34/architecture_applicative.git
cd architecture_applicative
echo "Quel est le nom de la branche à tester et déployer ?"
read nomBranche
git checkout $nomBranche
#On part du principe où le nom de la branche exite déjà
mvn clean
mvn test > test.result
failures=`grep "Tests run" test.result | tail -1 | cut -d "," -f 2 | cut -d " " -f 3`
errors=`grep "Tests run" test.result | tail -1 | cut -d "," -f 3 | cut -d " " -f 3`
skippeds=`grep "Tests run" test.result | tail -1 | cut -d "," -f 4 | cut -d " " -f 3`

rm test.result

if [ "$failures" -eq 0 ] && [ "$errors" -eq 0 ] && [ "$skippeds" -eq 0 ]
then
  echo "Installation en cours"
  mvn install
  mvn spring-boot:run &
  echo "Application déployer"
else
  echo "Des erreurs dans la phase de test sont présent. Aucune action supplémentaire n'est possible"
fi