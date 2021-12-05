
# APIs to be provided by backend

## Home Page: Get available contents

GET @ /highlights/available-contents

Return an array of content objects

```
[
	{
		"id":"content1",
		"title":"How to be politically correct",
		"type": "html",
		"imgUrl":"/highlights/content1/politics.jpg",
		"duration": "10m",
		"highlights": 23
	},
	{
		"id":"content2",
		"title":"How to play Soccer",
		"type": "pdf",
		"imgUrl":"/highlights/content2/soccer.png",
		"duration": "30m",
		"highlights": 0
	},
	{
		"id":"content3",
		"title":"Astronomy",
		"type": "video",
		"imgUrl":"/highlights/assets/placeholder.jpg",
		"duration": "1h",
		"highlights": 4
	}
]
```

## Home Page: Get viewed contents

GET @ /highlights/viewed-contents

*TODO: Should we unify these two APIs ?*

Return an array of content objects

```
[
	{
		"id":"content1",
		"title":"How to be politically correct",
		"type": "html",
		"imgUrl":"/highlights/content1/politics.jpg",
		"duration": "10m",
		"highlights": 23
	},
	{
		"id":"content2",
		"title":"How to play Soccer",
		"type": "pdf",
		"imgUrl":"/highlights/content2/soccer.png",
		"duration": "30m",
		"highlights": 0
	},
	{
		"id":"content3",
		"title":"Astronomy",
		"type": "video",
		"imgUrl":"/highlights/assets/placeholder.jpg",
		"duration": "1h",
		"highlights": 4
	}
]
```

## Home Page: Get highlight to show in the highlight portlet

GET @ /highlights/highlight

Return a highlight object

```
	{
		"id":"highlight1",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Today it is more important to be politically correct than to be right",
		"location" :{
			"ancestor-id":"main-content-div",
			"offset": 26
		},
		"context": {
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:14:36)"
	}
```

```
	{
		"id":"highlight2",
		"userId":"user4579",
		"contentId":"content3",
		"text":"day and night run in cycles",
		"location" :{
			"start":11.2,
			"duration": 12
		},
		"context": {
		},
		"type":"clip",
		"source": "video",
		"createdOn":"ISODate(2021-12-01Z2:14:36)"
	}

```

## Content Page: Get all highlights for this content by this user

GET @ /highlights/content/highlights

Return an array of highlight objects

```
[
	{
		"id":"highlight1",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Today it is more important to be politically correct than to be right",
		"location" :{
			"ancestor-id":"main-content-div",
			"offset": 26
		},
		"context": {
			"notes":"So true"
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:14:36)"
	},
	{
		"id":"highlight14",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Rights and responsibilities are two sides of the same coin",
		"location" :{
			"ancestor-id":"second-content-div",
			"offset": 43
		},
		"context": {
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:10:22)"
	},
	{
		"id":"highlight14",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Universal acceptance of our differences is the only way to move forward",
		"location" :{
			"ancestor-id":"conclusion-div",
			"offset": 0
		},
		"context": {
			"notes":"Will really be useful for the coming hackathon"
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:36:48)"
	}
]
```

## Content Page: Save a highlight 

POST @ /highlights/content/highlight/html
POST @ /highlights/content/highlight/pdf
POST @ /highlights/content/highlight/video

Return nothing. Request below.

```

	{
		"contentId":"content1",
		"text":"We are strong as long as we are in this together",
		"location" :{
			"ancestor-id":"main-content-div",
			"offset": 26
		},
		"context": {
			"notes":"Catchy phrase!"
		}
	}
```

## Personalization Page: Show all my highlights

GET @ /highlights/me

Return an array of highlight objects

```
[
	{
		"id":"highlight1",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Today it is more important to be politically correct than to be right",
		"location" :{
			"ancestor-id":"main-content-div",
			"offset": 26
		},
		"context": {
			"notes":"So true"
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:14:36)"
	},
	{
		"id":"highlight14",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Rights and responsibilities are two sides of the same coin",
		"location" :{
			"ancestor-id":"second-content-div",
			"offset": 43
		},
		"context": {
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:10:22)"
	},
	{
		"id":"highlight14",
		"userId":"user4576",
		"contentId":"content1",
		"text":"Universal acceptance of our differences is the only way to move forward",
		"location" :{
			"ancestor-id":"conclusion-div",
			"offset": 0
		},
		"context": {
			"notes":"Will really be useful for the coming hackathon"
		},
		"type":"text",
		"source": "html",
		"createdOn":"ISODate(2021-12-03Z12:36:48)"
	}
]
```

## Personalization Page: Remove a highlight

DELETE @ /highlights/me/{highlight-id}

Return nothing

## Personalization Page: Get my stylesheet for highlights

GET @ /highlights/me/style

Return a stylesheet generated dynamically for this user


## Personalization Page: Get my UI config for highlight

GET @ /highlights/me/config

Return a highlight config object

```
	{
		"userId":"user4576",
		"font-weight":"none", //bold | none
		"font-style":"none", //italic | none
		"text-decoration":"none", //underline | none
		"border-bottom-style":"none", //dotted | dashed  | none
		"border-top-style":"none", //dotted | dashed  | none
		"border-right-style":"none", //dotted | dashed  | none
		"border-left-style":"none", //dotted | dashed  | none
		"color": "red",
		"background-color": "white"
	}
```

## Personalization Page: Save my UI config for highlight

PUT @ /highlights/me/config

Return nothing. Submit a highlight config object

```
	{
		"userId":"user4576",
		"font-weight":"bold",
		"font-style":"italic",
		"text-decoration":"none",
		"border-bottom-style":"dashed",
		"border-top-style":"none",
		"border-right-style":"none",
		"border-left-style":"none",
		"color": "gray",
		"background-color": "white"
	}
```
