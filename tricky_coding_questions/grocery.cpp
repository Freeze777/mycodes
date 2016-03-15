int main()
{
  string S;
  int P;
  cin >> P >> S;
  P--;
  int res = 0;
  for(int i = 0; i < P; ++i) {
    if(S[i] == 'B') res++;
  }
  printf("%d\n", res);
  return(0);
} 
