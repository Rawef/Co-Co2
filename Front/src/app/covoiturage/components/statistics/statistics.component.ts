import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../../service/annonce.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  activeCount: number = 0;
  inactiveCount: number = 0;
  loading: boolean = true;
  error: string | null = null;

  constructor(private annonceService: AnnonceService) {}

  ngOnInit(): void {
    this.loadStatistics();
  }

  loadStatistics(): void {
    this.annonceService.getStatistics()
      .subscribe(
        (data: any) => {
          this.activeCount = data.active_count; 
          this.inactiveCount = data.inactive_count;
          this.loading = false;
        },
        (error: any) => {
          this.error = 'Failed to load statistics. Please try again.';
          this.loading = false;
        }
      );
  }

  calculatePercentage(value: number): string {
    const total = this.activeCount + this.inactiveCount;
    if (total === 0) {
      return '0%';
    }
    return ((value / total) * 100).toFixed(2) + '%';
  }
  
}
