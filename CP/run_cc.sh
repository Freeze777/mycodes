g++ run.cc -std=c++11 -o run.out
#g++ run.cc -o run.out
#gcc run.cc -o run.out
./run.out < input.txt
rm -r *.out
echo "actual o/p:"
cat output.txt
