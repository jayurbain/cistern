![http://www.cis.lmu.de/~sascha/CoSimRank/wordle.png](http://www.cis.lmu.de/~sascha/CoSimRank/wordle.png)

# Introduction #

The codes provided here will compute CoSimRank. You can also compute the cosine of personalized [PageRank](http://en.wikipedia.org/wiki/PageRank) vectors. Our experiments have shown that CoSimRank is more accurate.

# Get started #

  1. checkout the code via SVN: `svn checkout http://cistern.googlecode.com/svn/trunk/ cistern-read-only`
  1. go to the folder and compile the code (`make`)
  1. run `sandbox/sandbox.sh` to play around

# Graph files #

The graph files are in [matrix market](http://math.nist.gov/MatrixMarket/) file format. Each graph can contain several files, each one presenting one edge type.

The code comes with an English and a German graph, with approximately 30k words each. The graphs have no edges type (or just one).

# Dictionary files #

Each graph requires a dictionary file to connect nodes and words. A dictionary files contains N lines (where as N is the number of words in the graph, i.e. the matrix market files has dimension NxN). Each lines starts with the the index, followed by the word and the pos. All separated by space.

The codes also contains the matching dictionary files for the graphs.

# Seed file #

For bilingual experiments a seed file is required. This file contains known translations. It is also in [matrix market](http://math.nist.gov/MatrixMarket/) file file format and usually contains edges of weigth 1. But other weights might be interesting as well. For monolingual experiments (synonym extraction) this file is not used.

# Testset file #

You can provide a testset file if you want (see sandbox/example.sh for an example). The code will execute all tests and compute acc@1, acc@10 and MRR. A testset file starts with a line containing the number of tests T, the source graph and the target graph (either A or B). The following T lines will contain the pos (a, n or v), the source word and the expected answer. The anser can be several word separated by ",". All other entries are separated by tabulator.

The code has got a TS1000 testset for lexicon extraction and a TS68 testset for synonym extraction.