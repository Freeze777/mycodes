import scipy.stats as st

nrm=st.norm(70,10)
# print round(nrm.cdf(19),3)
# print round(1.0-nrm.cdf(21),3)
# print round(nrm.cdf(35)-nrm.cdf(30),3)
print round(1.0-nrm.cdf(80),4)*100
print round(1.0-nrm.cdf(60),4)*100
print round(nrm.cdf(60),4)*100
