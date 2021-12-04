# Highlights

## Vision

Cornerstone brings best-in class learning to our learners.

Learners have the opportunity to learn and consume content in a variety of devices/formats.

How does Cornerstone help the learner after the learner completes content ?

...

What value do we bring after content completion ?

## Retention

The worst-best-case scenario is all too familiar.....

- Learner finds exactly what she is looking for
- Learner loves the content and engages with it
- Learner learns much about the subject

A few weeks later...
- Learner does not remember much of what she had learnt.
- Knowledge retention seems to follow the Power law. 
- It decreases as the square of time.

## Personal Experience
```
I started off learning React.
2 production issues cropped up
Had to also continue on a sprint delivery story that was slightly complicated.

When I did get back to React, it felt a few years had passed.
Was pretty dis-oriented and had to start from scratch again.
```

## Words of the wise....

### R epetition
### R e-inforces
### R etention

* What are we repeating ?
	- Highlights
* How are we re-inforcing ?
	- When the user logs in, we jog the user's memory with highlights and the context
What is value addition ?
	Retention

## Other Approaches
- Spaced repetition
- Flashcards

## Highlight Approach 
1. Highlight relevant parts of content 
2. Repeat these highlights whenever the user logs-on
3. Re-inforce learning with every repetition
4. Voila - Retention

## Implementation

1. Highlight
	- Html
		- Select relevant text
	- File
		- Select relevant text
	- Video
		- Select time duration
2. Save highlights
3. Highlighted on reload
   -Re-loading same content, user selected text/video is rendered differently
4. Highlights portlet displays a single highlight depending on the re-inforcing function (oldest/most recent/one per content/...)
5. User should be able to personalize the highlight effect and icon

## Future

We can open this up.
- Social: Show the most highlighted text in content
- Measure of engagement: Content with most highlighs is engaging content
- Create a tldr; from highlights
- Input to ML
- Searchable highlights
- Build up a knowledge base for each user from their highlights

## Questions

**What about other file types ?**

Files that can be rendered natively in the browser can be highighted. If a user downloads the file, this functionality will not be supported.

**What are the limitations ?**

- No images in selection
- Text/Time only

**Can the re-inforcing function be configured ?**

Yes. A few of the options are 
- Chronological 
- Reverse-chronological
- Matching most recent
- Bubble-up
- Periodic
- Random
- One per day

**Can the highlight icon and color scheme be configured ?**

Very easily.

**Concerns on integrating this to CSOD products**
- Accessibility
- Integration to content player

**Are there other channels to re-inforce ?**

Sure we can use periodic notifications to send out a highlight once a week/month/...
