export interface HtmlData {
  data: string;
  selectedText: string[];
}

export interface ContentDetails {
  id: string;
  title: string;
  type: string;
  imgUrl: string;
  duration: string;
  highlights: number;
  launchUrl?: string;
}

export interface HighlightData {
  contentId: string;
  context: {
    note: string;
  };
  createdOn?: string;
  id?: string;
  location: {
    ancestorId: string;
    offset: number;
  };
  source: string;
  text: string;
  trim: {
    from: string;
    to: string;
  };
  type: string;
  updatedOn?: string;
  userId?: string;
}
