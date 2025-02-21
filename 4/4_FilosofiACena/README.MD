# Dining philosophers

This code example represents the structure of the "dining philosophers" problem.
This repository will be updated with the full code after the students have worked on their solution.

## Problem description

The problem involves a group of N philosophers sitting at a table, each with a plate of food and a fork to their left and right.
To eat, a philosopher needs both forks and can use only the ones at its immediate left or right. There are N forks in total.

## Mistakes to avoid

* We should avoid having always the same philosophers eating;
* We should avoid that a philosopher may keep its fork always for himself, never putting it down;
* We should avoid starvation.