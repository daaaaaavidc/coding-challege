#!/usr/bin/env bash

INPUT_FILE="./tweet_input/tweets.txt"
OUTPUT_FILE1="./tweet_output/ft1.txt"
OUTPUT_FILE2="./tweet_output/ft2.txt"

sbt "run-main com.github.daaaaaavidc.codingChallenge.Feature1"
sbt "run-main com.github.daaaaaavidc.codingChallenge.Feature2"
