## Overview

In daily fantasy sports, contestants compete for prize money by selecting the lineup that
accumulates the maximum number of points subject to certain salary and roster constraints. Anyone
that has played a few times inevitably wonders whether the *optimal* lineup can be selected given
the points, salary, and position of the various players. It can. This optimization problem is a kind
of [mixed integer linear program] for which the `optimize` library provides a type-safe and
declarative solution.

## Linear Programming

A linear program maximizes or minimizes an *objective* subject to certain *constraints*. An
objective is a linear function of some variables (e.g., `x + 2 * y`), and a constraint is a linear
bound on the values that those variables can take (e.g., `3 * x + y <= 8`). Typically, variables are
real-valued. Linear programs with both real-valued and integer-valued variables are referred to as
mixed integer linear programs, and can be formulated and solved by `optimize`.

```scala
import fantasy.optimize._
import fantasy.optimize.solvers.GLPK

// Construct the optimization problem.
val x = Real("x")
val y = Integer("y")
val z = Binary("z")

val problem = Maximize(
  3 * x + 4 * y - 2 + z,
  2 * x <= 5.2 * y,
  x / 3 === y,
  x >= 4 * z,
)

// Solve the problem.
val solution = GLPK.solve(problem)
solution.optimum
solution.value(x)
solution.value(y)
solution.value(z)

// Linearity is enforced at compile-time.
x + y
x * y // error
x - (3 + y)
x / (3 + y) // error
```

## Lineup Optimization

Given a vector of players, `players[i]`.

* Let `position[i][j]` be whether or not player `j` plays position `i`.
* Let `points[i]` be the number of points that player `i` is projected to score.
* Let `salary[i]` be the amount of money that player `i` costs to select.
* Let `selected[i]` be whether or not player `i` is selected in the optimal lineup.

Then, the objective is to maximize `selected ⋅ points` under salary constraints like
`selected ⋅ salary <= 50000` and roster constraints like `selected ⋅ position[qb] == 1`. Because the
dot product is a linear operator and the vectors are not all real-valued, this optimization problem
is a mixed integer linear program that can be solved by the `optimize` library.

```scala
import fantasy.optimize.Lineup._
import fantasy.optimize.solvers.GLPK

// Construct the sequence of possible players.
val players = Seq(
  Player(name = "Stephen Curry", position = "PG", points = 62.7, salary = 9000),
  Player(name = "Klay Thompson", position = "SG", points = 34.5, salary = 7500),
)

// Determine the optimal lineup of players subject to certain lineup rules.
val lineup = Lineup.optimal(
  players,
  Rule.SalaryCap(50000),
  Rule.SalaryFloor(10000),
  Rule.UniquePlayers,
)
```

## References

- [Optimus]
- [pydfs-lineup-optimizer]

[mixed integer linear program]:
  https://en.wikipedia.org/wiki/Integer_programming
[Optimus]:
  https://github.com/vagmcs/Optimus
[pydfs-lineup-optimizer]:
  https://github.com/DimaKudosh/pydfs-lineup-optimizer
